package com.mds.my.platform.lostandfound.project.system.service.impl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.NoticeVO;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.mds.my.platform.lostandfound.project.system.mapper.SysNoticeMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysNoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService{

    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result saveNotice(SysNotice sysNotice) {
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        sysNotice.setAgree(0);
        sysNotice.setViews(0);
        sysNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysNotice.setCreateUser(user.getId());
        sysNotice.setIsDelete(0);
        int i = sysNoticeMapper.insert(sysNotice);
        if (i>0){
            return Result.success("发布成功！");
        }
        return Result.fail("发布失败！");
    }

    @Override
    public Result updateNotice(SysNotice sysNotice) {
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        SysNotice notice = sysNoticeMapper.selectById(sysNotice.getId());
        if (Objects.isNull(notice)){
            return Result.fail("对应数据不存在！");
        }
        sysNotice.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sysNotice.setUpdateUser(user.getId());
        int i = sysNoticeMapper.updateById(sysNotice);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败！");
    }

    @Override
    public Result removeNotice(Integer id) {
        SysNotice sysNotice = sysNoticeMapper.selectById(id);
        if (Objects.isNull(sysNotice)){
            return Result.fail("删除失败，数据不存在！");
        }
        SysNotice not = new SysNotice();
        not.setId(id);
        not.setIsDelete(1);
        int i = sysNoticeMapper.updateById(not);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    @Override
    public Result getNoticeInfo(Integer id) {
        SysNotice notice = sysNoticeMapper.selectById(id);
        return Result.success("",notice);
    }

    @Override
    public PageResult noticeList(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        List<NoticeVO> vo = sysNoticeMapper.findAll(params);
        PageInfo<NoticeVO> info = new PageInfo<>(vo);

        return PageResult.<NoticeVO>builder()
                .data(info.getList())
                .total(info.getTotal())
                .msg("获取公告列表成功！")
                .build();
    }

    @Override
    public Result getLb(Map<String, Object> params) {
        params.put("type",1);
        List<SysNotice> lb = sysNoticeMapper.findLb(params);
        return Result.success("获取轮播成功！",lb);
    }

}

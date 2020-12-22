package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.vo.CateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCateMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;
import com.mds.my.platform.lostandfound.project.system.service.SysCateService;

@Service
public class SysCateServiceImpl extends ServiceImpl<SysCateMapper, SysCate> implements SysCateService {
    @Autowired
    private SysCateMapper sysCateMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result saveCate(SysCate sysCate) {
        if (Objects.isNull(sysCate)) {
            return Result.fail("提交数据不能为空！");
        }
        sysCate.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysCate.setCreateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        int i = sysCateMapper.insert(sysCate);
        if (i > 0) {
            return Result.success("添加成功！");
        }
        return Result.fail("添加失败！");
    }

    @Override
    public PageResult findAll(Map<String, Object> params) {
        List<CateVO> sysCates = sysCateMapper.findAll(params);
        if (!Objects.isNull(sysCates) && !sysCates.isEmpty()) {
            //父级分类
            List<CateVO> pList = sysCates.stream().filter(val -> val.getPid() == null || val.getPid() == 0).collect(Collectors.toList());
            if (!Objects.isNull(pList) && !pList.isEmpty()) {
                pList.forEach(val -> {
                    getChildren(sysCates, val);
                });
            }
            PageInfo<CateVO> info = new PageInfo<>(pList);
            return PageResult.<CateVO>builder().code(200).msg("获取分类成功！").total(info.getTotal()).data(info.getList()).build();
        }
        return PageResult.builder().code(201).msg("无数据！").build();
    }

    @Override
    public Result getGoodsCateList(Integer pid) {
        List<SysCate> cates = sysCateMapper.getCateByPid(pid);
        return Result.success("获取物品分类成功！",cates);
    }

    @Override
    public Result removeCate(Integer id) {
        SysCate sysCate = sysCateMapper.selectById(id);
        if (Objects.isNull(sysCate)){
            return Result.fail("删除失败，该系统分类不存在！");
        }
        int i = sysCateMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    private void getChildren(List<CateVO> list, CateVO cateVO) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return;
        }
        List<CateVO> children = new ArrayList<>();
        list.forEach(val -> {
            if (val.getPid().equals(cateVO.getId())) {
                children.add(val);
            }
        });

        //getChildren(children);
        if (children.isEmpty()) {
            return;
        }
        cateVO.setChildren(children);
        children.forEach(val -> {
            getChildren(list, val);
        });
    }
}


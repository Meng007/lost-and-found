package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.UserInfoVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserInfoMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import com.mds.my.platform.lostandfound.project.system.service.SysUserInfoService;
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements SysUserInfoService{

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public Result getInfo() {
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        if (Objects.isNull(user)){
            return Result.fail("用户未登录！");
        }
        UserInfoVO vo = new UserInfoVO();
        SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(user.getId());
        BeanUtils.copyProperties(sysUserInfo,vo);
        SysUser one = sysUserMapper.selectById(user.getId());
        vo.setUser(one);
        return Result.success("获取用户拓展信息成功！",vo);
    }
}

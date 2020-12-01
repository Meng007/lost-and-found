package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
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
    @Override
    public Result getInfo() {
        Integer userId = tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId();
        if (Objects.isNull(userId)){
            return Result.fail("用户未登录！");
        }
        SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(userId);

        return Result.success("获取用户拓展信息成功！",sysUserInfo);
    }
}

package com.mds.my.platform.lostandfound.framework.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.enums.UserStatus;
import com.mds.my.platform.lostandfound.common.exception.BaseException;
import com.mds.my.platform.lostandfound.framework.security.LoginUser;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserInfoMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysUser::getUsername,username);
        SysUser user = sysUserService.getOne(lqw);
        System.out.println("用户："+user);
        if (Objects.isNull(user)){
            log.info("登录用户：{}不存在",username);
            throw new BaseException("登录用户："+username+"不存在!");
        }else if (new Integer(1).equals(user.getIsDelete())){
            log.info("登录用户：{}被删除",username);
            throw new BaseException("对不起，你的帐号："+username+"已删除");
        }else if (UserStatus.DISABLE.getCode().equals(user.getStatus())){
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        SysUserInfo info = new SysUserInfo();
        info.setUserId(user.getId());
        info.setLastLogin(new Timestamp(System.currentTimeMillis()));
        sysUserInfoMapper.updateById(info);
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user, sysPermissionService.getMenuPermission(user));
    }
}

package com.mds.my.platform.lostandfound.framework.security.service;

import com.mds.my.platform.lostandfound.common.enums.UserStatus;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysMenuService;
import com.mds.my.platform.lostandfound.project.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SysPermissionService {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 获取菜单数据权限
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserStatus.USER_TYPE.getCode().equals(user.getUserType())){
            perms.add("*:*:*");
        } else {
            perms.addAll(sysMenuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }

    /**
     *  获取角色
     * @param user
     * @return
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserStatus.USER_TYPE.getCode().equals(user.getUserType())){
            roles.add("admin");
        }else{
            roles.addAll(sysRoleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }
}

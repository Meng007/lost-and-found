package com.mds.my.platform.lostandfound.project.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.LoginBody;
import com.mds.my.platform.lostandfound.framework.security.LoginUser;
import com.mds.my.platform.lostandfound.framework.security.service.LoginService;
import com.mds.my.platform.lostandfound.framework.security.service.SysPermissionService;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.dto.UserDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;
import com.mds.my.platform.lostandfound.project.system.service.SysMenuService;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 13557
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @param loginBody username password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        Map<String, String> result = new HashMap<>(1);
        result.put("token", token);
        return Result.success("登录成功！", result);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public Result getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = sysPermissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = sysPermissionService.getMenuPermission(user);
        Map<String, Object> result = new HashMap<>(3);
        LambdaQueryWrapper<SysUser> lab = new LambdaQueryWrapper<>();
        lab.eq(SysUser::getId,user.getId());
        SysUser one = sysUserService.getOne(lab);
        result.put("user", one);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return Result.success("获取用户信息成功！", result);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Result getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenuVO> menus = sysMenuService.selectMenuTreeByUserId(user);
        return Result.success("获取路由成功！", sysMenuService.buildMenus(menus));
    }

    /**
     * 用户注册
     */
    @PostMapping("/reg/user")
    public Result reg(@RequestBody UserDTO userDTO){
        return sysUserService.regUser(userDTO);
    }
}

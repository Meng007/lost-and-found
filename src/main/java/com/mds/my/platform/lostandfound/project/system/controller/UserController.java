package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysUserInfoService;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserInfoService sysUserInfoService;

    /**
     *  修改密码
     */
    @PutMapping("/update/password/{id}")
    public Result updatePassword(@RequestBody SysUser sysUser, @PathVariable Integer id){
        sysUser.setId(id);
        return sysUserService.updatePassword(sysUser);
    }
}

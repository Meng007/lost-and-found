package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.config.SystemParamsConfig;
import com.mds.my.platform.lostandfound.project.system.domain.dto.UserDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysUserInfoService;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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
    @PutMapping("/update/password")
    public Result updatePassword(@RequestBody UserDTO userDTO){
        return sysUserService.updatePassword(userDTO);
    }
    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo(){
        return sysUserInfoService.getInfo();
    }

    /**
     * 修改用户头像
     */
    @PostMapping("/avatar")
    public Result updateAvatar(@RequestParam("file") MultipartFile multipartFile){
        return sysUserService.updateAvatar(multipartFile);
    }


    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysUserService.getUserList(params);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysUserService.removeUser(id);
    }

    /**
     * 获取登录用户
     */
    @GetMapping("/login/list")
    public PageResult loginList(@RequestParam Map<String,Object> params){
        return sysUserService.getLoginUserList(params);
    }

    @PutMapping("/update")
    public Result update( @RequestBody UserDTO userDTO){
        return sysUserService.updateUserInfo(userDTO);
    }


}

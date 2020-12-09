package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.mds.my.platform.lostandfound.project.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     *  保存角色
     * @param sysRole
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole){
        return sysRoleService.saveRole(sysRole);
    }

    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysRoleService.findAll(params);
    }

}

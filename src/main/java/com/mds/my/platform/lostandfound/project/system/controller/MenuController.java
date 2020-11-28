package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private TokenService tokenService;

    /**
     *  新增菜单
     * @param sysMenu
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        return sysMenuService.saveMenu(sysMenu);
    }
}

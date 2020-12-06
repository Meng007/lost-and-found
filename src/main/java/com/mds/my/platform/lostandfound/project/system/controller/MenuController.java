package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     *  新增菜单
     * @param sysMenu
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        return sysMenuService.saveMenu(sysMenu);
    }

    /**
     *  修改菜单
     * @param sysMenu
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu){
        return sysMenuService.updateMenu(sysMenu);
    }

    /**
     *  删除菜单
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysMenuService.removeMenu(id);
    }

    @GetMapping("/info/{id}")
    public Result getMenuInfo(@PathVariable Integer id){
        return sysMenuService.getMenuInfo(id);
    }

    @GetMapping("/list")
    public Result list(@RequestParam Map<String,Object> params){
        return sysMenuService.findAll(params);
    }
}

package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;
import com.mds.my.platform.lostandfound.project.system.service.SysCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cate")
public class CateController {
    @Autowired
    private SysCateService sysCateService;

    /**
     * 添加分类
     */
    @PostMapping("/save")
    public Result addCate(@RequestBody SysCate sysCate){
        return sysCateService.saveCate(sysCate);
    }

    /**
     * 后台获取分类列表
     */
    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysCateService.findAll(params);
    }

    /**
     * 获取物品分类
     */
    @GetMapping("/goods/list/{pid}")
    public Result goodsCate(@PathVariable Integer pid){
        return sysCateService.getGoodsCateList(pid);
    }
}

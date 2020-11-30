package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictType;
import com.mds.my.platform.lostandfound.project.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/dict-type")
public class DictTypeController {
    @Autowired
    private SysDictTypeService sysDictTypeService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/save")
    public Result save(@RequestBody SysDictType sysDictType){
        return sysDictTypeService.saveDictType(sysDictType);
    }

    @GetMapping("/list")
    public PageResult list(@RequestParam Map params){
        return sysDictTypeService.findAll(params);
    }

    @GetMapping("/select")
    public Result getList(){
        return Result.success("获取字典分类成功！",sysDictTypeService.list());
    }
}

package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.mds.my.platform.lostandfound.project.system.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author 13557
 */
@RestController
@RequestMapping("/dict-data")
public class DictDataController {
    @Autowired
    private SysDictDataService sysDictDataService;

    @PostMapping("/save")
    public Result save(@RequestBody SysDictData sysDictData){
        return sysDictDataService.saveDictData(sysDictData);
    }

    /**
     * 字典数据列表
     */
    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysDictDataService.findAll(params);
    }

    /**
     *  删除字典数据
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysDictDataService.removeDictData(id);
    }

    /**
     *  字典数据修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody SysDictData sysDictData){
        return sysDictDataService.updateDictData(sysDictData);
    }

    /**
     *  根据字典类型获取字典数据
     */

    @GetMapping("/{type}/list")
    public Result list(@PathVariable Integer type){
        return sysDictDataService.getDictDataByType(type);
    }
}

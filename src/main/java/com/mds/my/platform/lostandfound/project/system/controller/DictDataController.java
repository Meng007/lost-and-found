package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.mds.my.platform.lostandfound.project.system.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}

package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import com.mds.my.platform.lostandfound.project.system.service.SysSensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/sensitive-word")
public class SensitiveWordController {
    @Autowired
    private SysSensitiveWordService sysSensitiveWordService;

    @PostMapping("/save")
    public Result save(@RequestBody SysSensitiveWord sysSensitiveWord){
        return sysSensitiveWordService.saveWord(sysSensitiveWord);
    }
}

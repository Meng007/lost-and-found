package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import com.mds.my.platform.lostandfound.project.system.service.SysSensitiveWordService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  敏感词修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody SysSensitiveWord sysSensitiveWord){
        return sysSensitiveWordService.updateSensitiveWord(sysSensitiveWord);
    }

    /**
     *  敏感词删除
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysSensitiveWordService.removeSensitiveWord(id);
    }

    /**
     * 数据回显
     */
    @GetMapping("/echo/{id}")
    public Result echo(@PathVariable Integer id){
        return sysSensitiveWordService.echo(id);
    }
    /**
     *  导出铭感词
     */
}

package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageDTO;
import com.mds.my.platform.lostandfound.project.system.service.SysCommentService;
import com.mds.my.platform.lostandfound.project.system.service.SysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private SysCommentService sysCommentService;

    @PostMapping("/save")
    public Result save(@RequestBody MessageDTO messageDTO){
        return sysCommentService.saveMessage(messageDTO);
    }

    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysCommentService.findAll(params);
    }

    @GetMapping("/my/list")
    public PageResult myList(@RequestParam Map<String,Object> params){
        return sysCommentService.findMyAll(params);
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysCommentService.removeMessage(id);
    }

}

package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.mds.my.platform.lostandfound.project.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @PostMapping("/save")
    public Result save(@RequestBody SysNotice sysNotice){
        return sysNoticeService.saveNotice(sysNotice);
    }

    @PutMapping("/update")
    public Result update(@RequestBody SysNotice sysNotice){
        return sysNoticeService.updateNotice(sysNotice);
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id){
        return sysNoticeService.removeNotice(id);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable Integer id){
        return sysNoticeService.getNoticeInfo(id);
    }

    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String,Object> params){
        return sysNoticeService.noticeList(params);
    }
}

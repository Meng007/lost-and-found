package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.mds.my.platform.lostandfound.project.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @GetMapping("/lb")
    public Result getLb(@RequestParam Map<String, Object> params) {
        return sysNoticeService.getLb(params);
    }
}
package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysAbout;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 13557
 */
@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private SysAboutService sysAboutService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/save")
    public Result save(@RequestBody SysAbout sysAbout){
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        Integer aid = 1;
        SysAbout about = sysAboutService.getById(aid);
        if (Objects.isNull(about)){
            //新增
            sysAbout.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysAbout.setCreateUser(user.getId());
            sysAboutService.save(sysAbout);
            return Result.success("发布成功！");
        }
        //修改
        about.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        about.setCreateUser(user.getId());
        about.setContent(sysAbout.getContent());
        sysAboutService.updateById(about);

        return Result.success("修改成功！");
    }

    @GetMapping("/info")
    public Result info (){
        Integer aid = 1;
        SysAbout about = sysAboutService.getById(aid);
        return Result.success("获取关于我们成功！",about);
    }
}

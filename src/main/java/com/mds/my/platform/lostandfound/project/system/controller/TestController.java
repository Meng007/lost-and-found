package com.mds.my.platform.lostandfound.project.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysMessageService;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysMessageService sysMessageService;
    @PostMapping("/add")
    public Result test(@RequestBody SysUser sysUser) throws IOException {
       sysMessageService.saveMessage();
        /* //HttpServletRequest request = ServletUtils.getRequest();
        request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("UTF-8");
        @Cleanup
        InputStream in = request.getInputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        String s = "";
        while ((len =in.read(bytes,0,bytes.length)) !=-1){
             s= new String(bytes,0,len);
            SysUser sysUser = JSONObject.parseObject(s, SysUser.class);
            System.out.println(s);
            System.out.println("对象"+sysUser);
        }*/
        return Result.success("1024",sysUser);
    }

    @GetMapping("/get")
    public Result test02(@RequestParam Map params){
        return Result.success("2048",params);
    }
}

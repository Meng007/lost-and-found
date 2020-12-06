package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.framework.filter.RepeatedlyRequestWrapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import lombok.Cleanup;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMessageMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysMessageService;
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService{

    @Override
    public void saveMessage() throws IOException {
        RepeatedlyRequestWrapper request = (RepeatedlyRequestWrapper)ServletUtils.getRequest();
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
        }
    }
}

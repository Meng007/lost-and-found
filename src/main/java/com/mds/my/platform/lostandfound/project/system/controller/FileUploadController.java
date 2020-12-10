package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.utils.file.FileUploadUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.config.ServerConfig;
import com.mds.my.platform.lostandfound.framework.config.SystemParamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13557
 */
@RestController
public class FileUploadController {

    @Autowired
    private ServerConfig serverConfig;

    @PostMapping("/file/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        String filePath = SystemParamsConfig.getUploadPath();
        try {
            Map<String,Object> result = new HashMap<>(2);
            String fileName = FileUploadUtils.upload(filePath,file);
            String fileUrl = serverConfig.getUrl() + fileName;
            result.put("fileName",fileName);
            result.put("fileUrl",fileUrl);
            return Result.success("文件上传成功！",result);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("文件上传失败！");
        }
    }
}

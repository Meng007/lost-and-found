package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

public interface SysMessageService extends IService<SysMessage>{


    /**
     * 保存test
     */
    void saveMessage() throws IOException;
}

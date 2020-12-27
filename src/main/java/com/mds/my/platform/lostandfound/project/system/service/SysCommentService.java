package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 13557
 */
public interface SysCommentService extends IService<SysComment>{


    Result saveMessage(MessageDTO messageDTO);

    PageResult findAll(Map<String, Object> params);

    PageResult findMyAll(Map<String, Object> params);

    Result removeMessage(Integer id);
}

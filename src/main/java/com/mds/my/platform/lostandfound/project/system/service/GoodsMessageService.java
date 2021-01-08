package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 13557
 */
public interface GoodsMessageService extends IService<GoodsMessage> {

    /**
     *  物品留言
     * @param goodsMessage
     * @return
     */
    Result saveMessage(GoodsMessage goodsMessage);

    /**
     *  物品留言列表
     * @param params
     * @return
     */
    PageResult messageList(Map<String, Object> params);

    /**
     * 删除留言
     * @param messageId
     * @param userId
     * @return
     */
    Result removeMessage(Integer messageId, Integer userId);

    Result saveComm(GoodsMessage goodsMessage);
}


package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.aspectj.lang.Message;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  物品留言操作 -> controller
 * @author 13557
 */
@RestController
@RequestMapping("/goods-message")
public class GoodsMessageController {
    @Autowired
    private GoodsMessageService goodsMessageService;

    /**
     *  物品 -> 留言
     */
    @PostMapping("/save")
    @Message(type = "goodsMessage")
    public Result save(@RequestBody GoodsMessage goodsMessage){
        return goodsMessageService.saveMessage(goodsMessage);
    }

    /**
     *  物品留言 -> 列表
     */
    @GetMapping("/goods/{id}")
    public PageResult list(@RequestParam Map<String,Object> params,@PathVariable Integer id){
        params.put("id",id);
        return goodsMessageService.messageList(params);
    }
    /**
     * 物品留言 -> 删除
     */
    @DeleteMapping("/remove/{messageId}/by/{userId}")
    public Result remove(@PathVariable Integer messageId,@PathVariable Integer userId){
        return goodsMessageService.removeMessage(messageId,userId);
    }
}

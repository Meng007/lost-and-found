package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessageAgree;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageAgreeService;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13557
 */
@RestController
@RequestMapping("msg")
public class MessageCommentController {

    @Autowired
    private GoodsMessageService goodsMessageService;
    @Autowired
    private GoodsMessageAgreeService goodsMessageAgreeService;

    /**
     * 留言
     * @param goodsMessage
     * @return
     */
    @PostMapping("/comm/save")
    public Result saveComm(@RequestBody GoodsMessage goodsMessage){
        return goodsMessageService.saveComm(goodsMessage);
    }

    /**
     *  留言点赞
     * @param goodsMessageAgree
     * @return
     */
    @PostMapping("/comm/agree")
    public Result agree(@RequestBody GoodsMessageAgree goodsMessageAgree){
        return goodsMessageAgreeService.saveAgree(goodsMessageAgree);
    }
}

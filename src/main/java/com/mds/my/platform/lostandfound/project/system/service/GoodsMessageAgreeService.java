package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessageAgree;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 *  @author mds
 *
 *  @date 12:55
 *  @description 物品留言点赞业务
 */
public interface GoodsMessageAgreeService extends IService<GoodsMessageAgree>{


    Result saveAgree(GoodsMessageAgree goodsMessageAgree);
}

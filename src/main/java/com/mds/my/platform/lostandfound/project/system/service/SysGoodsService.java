package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 1355
 */
public interface SysGoodsService extends IService<SysGoods>{

    /**
     *  保存物品
     * @param goodsDTO 物品消息
     * @return
     */
    Result saveGoods(GoodsDTO goodsDTO);

    /**
     *  获取物品列表
     * @param params 请求参数
     * @return
     */
    PageResult getGoodsList(Map params);
}

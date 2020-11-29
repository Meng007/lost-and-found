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

    /**
     *  我的物品列表
     * @param params
     * @return
     */
    PageResult getGoodsListByUserId(Map params);

    /**
     *  修改物品信息
     * @param goodsDTO
     * @return
     */
    Result updateGoods(GoodsDTO goodsDTO);

    /**
     *  删除物品
     * @param id
     * @return
     */
    Result removeGoods(Integer id);

    /**
     *  设置归还状态
     * @param id
     * @return
     */
    Result setGoodsStatus(Integer id);
}

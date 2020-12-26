package com.mds.my.platform.lostandfound.project.system.controller;

import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.common.enums.GoodsStatus;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import com.mds.my.platform.lostandfound.project.system.service.SysGoodsService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author 13557
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private SysGoodsService sysGoodsService;

    /**
     *  发布物品
     * @param goodsDTO 物品信息dto
     * @return 结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody GoodsDTO goodsDTO){
        return sysGoodsService.saveGoods(goodsDTO);
    }

    /**
     *  获取物品列表
     * @param params
     * @return
     */
    @GetMapping("/{type}/list")
    public PageResult list(@RequestParam Map params,@PathVariable String type){
        if (Constants.LOST.equals(type)){
            params.put("goodsStatus", GoodsStatus.GOODS_LOST.getCode());
        }else if (Constants.TAKE.equals(type)){
            params.put("goodsStatus",GoodsStatus.GOODS_TAKE.getCode());
        }
        params.put("status",new Integer(1));
        return sysGoodsService.getGoodsList(params);
    }

    /**
     *  我的物品列表
     * @param params
     * @param params
     * @return
     */
    @GetMapping("/my-list")
    public PageResult myGoods(@RequestParam Map<String,Object> params){

        return sysGoodsService.getGoodsListByUserId(params);
    }

    /**
     * 物品编辑
     */
    @PutMapping("/update")
    public Result update(@RequestBody GoodsDTO goodsDTO){
        return sysGoodsService.updateGoods(goodsDTO);
    }

    /**
     *  物品删除
     */
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Integer id ){
        return sysGoodsService.removeGoods(id);
    }

    /**
     * 后台获取物品列表
     */
    @GetMapping("/list")
    public PageResult adminGoodsList(@RequestParam Map params){
        return sysGoodsService.getGoodsList(params);
    }

    /**
     * 获取物品详情
     */
    @GetMapping("/info/{id}")
    public Result getGoodsInfo(@PathVariable Integer id){
        return sysGoodsService.getGoodsInfo(id);

    }

    /**
     * 物品搜索
     */
    @GetMapping("/search")
    public PageResult search(@RequestParam Map<String,Object> params){
        return sysGoodsService.getGoodsList(params);
    }
}

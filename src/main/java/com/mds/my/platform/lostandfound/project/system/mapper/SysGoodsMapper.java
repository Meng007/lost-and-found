package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 13557
 */
@Mapper
public interface SysGoodsMapper extends BaseMapper<SysGoods> {
    /**
     *  保存物品图片
     * @param goodsDTO
     * @return
     */
    void saveImages(GoodsDTO goodsDTO);

    /**
     *  查早所有物品
     * @param params 请求参数
     * @return
     */
    List<GoodsVO> findAll(Map params);
}
package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoodsImage;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     *  我的物品列表
     * @param params
     * @return
     */
    List<GoodsVO> getMyGoodsList(Map params);

    /**
     *  删除图片
     * @param id
     */
    @Delete("delete from sys_goods_image where goods_id = #{id}")
    void deleteImage(@Param("id") Integer id);

    GoodsVO getGoodsInfo(Integer id);

    List<SysGoodsImage> getImages(Integer id);

    GoodsVO getGoodsInfo1(Integer id);
}
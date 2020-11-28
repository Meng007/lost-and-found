package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物品图片
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_goods_image")
public class SysGoodsImage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物品id
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 图片路径
     */
    @TableField(value = "image_path")
    private String imagePath;

    public static final String COL_ID = "id";

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_IMAGE_PATH = "image_path";
}
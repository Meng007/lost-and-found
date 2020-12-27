package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 留言图片
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_comm_image")
public class SysCommImage {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 留言id
     */
    @TableField(value = "comment_id")
    private Integer commentId;

    /**
     *  文件路径
     */
    @TableField(value = "image_path")
    private String imagePath;

    public static final String COL_ID = "id";

    public static final String COL_COMMENT_ID = "comment_id";

    public static final String COL_IMAGE_PATH = "image_path";
}
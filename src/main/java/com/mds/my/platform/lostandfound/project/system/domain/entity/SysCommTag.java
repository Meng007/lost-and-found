package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 话题标签关联表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_comm_tag")
public class SysCommTag {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 话题id
     */
    @TableField(value = "comment_id")
    private Integer commentId;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    public static final String COL_ID = "id";

    public static final String COL_COMMENT_ID = "comment_id";

    public static final String COL_TAG_ID = "tag_id";
}
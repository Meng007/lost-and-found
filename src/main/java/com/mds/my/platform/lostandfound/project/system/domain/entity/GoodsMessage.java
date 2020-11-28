package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 物品留言表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_message")
public class GoodsMessage {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     *  消息id
     */
    @TableField(value = "message_id")
    private Integer messageId;

    /**
     * 留言人id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 留言时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 留言物品id
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 留言状态：1通过，0待审核
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 点赞数
     */
    @TableField(value = "agree")
    private Integer agree;

    /**
     * 留言内容
     */
    @TableField(value = "message_content")
    private String messageContent;

    /**
     * 逻辑删除 1删除，0为删除
     */
    @TableField(value = "is_delete")
    private Boolean isDelete;

    public static final String COL_ID = "id";

    public static final String COL_MESSAGE_ID = "message_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_STATUS = "status";

    public static final String COL_AGREE = "agree";

    public static final String COL_MESSAGE_CONTENT = "message_content";

    public static final String COL_IS_DELETE = "is_delete";
}
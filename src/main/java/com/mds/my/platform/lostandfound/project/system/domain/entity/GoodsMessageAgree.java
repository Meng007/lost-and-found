package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物品留言点赞记录表
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_message_agree")
public class GoodsMessageAgree {
    /**
     * 点赞用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * 留言id
     */
    @TableField(value = "message_id")
    private Integer messageId;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_MESSAGE_ID = "message_id";
}
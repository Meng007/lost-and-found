package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 消息通知表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_message")
public class SysMessage {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息类型：1系统消息，2留言消息，
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 发布消息人的id
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 接收消息人的id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 消息状态，1已读，0未读
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 逻辑删除 1删除，0为删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_MESSAGE = "message";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_DELETE = "is_delete";
}
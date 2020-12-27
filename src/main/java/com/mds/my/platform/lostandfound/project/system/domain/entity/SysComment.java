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
    * 留言
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_comment")
public class SysComment {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 留言内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 评论数
     */
    @TableField(value = "message_num")
    private Integer messageNum;

    /**
     * 点赞数
     */
    @TableField(value = "agree_num")
    private Integer agreeNum;

    /**
     * 发布日期
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 状态 1 正常
     */
    @TableField(value = "`status`")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_TAG_ID = "tag_id";

    public static final String COL_VIEWS = "views";

    public static final String COL_MESSAGE_NUM = "message_num";

    public static final String COL_AGREE_NUM = "agree_num";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_STATUS = "status";
}
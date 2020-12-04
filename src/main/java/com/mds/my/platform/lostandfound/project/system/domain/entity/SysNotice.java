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
    * 系统公告
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_notice")
public class SysNotice {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 封面/轮播
     */
    @TableField(value = "cover_image")
    private String coverImage;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 链接到别的物品内容
     */
    @TableField(value = "url")
    private String url;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 发布日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 发布人id
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 更新日期
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人id
     */
    @TableField(value = "update_user")
    private Integer updateUser;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 点赞数
     */
    @TableField(value = "agree")
    private Integer agree;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_COVER_IMAGE = "cover_image";

    public static final String COL_CONTENT = "content";

    public static final String COL_URL = "url";

    public static final String COL_TYPE = "type";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_VIEWS = "views";

    public static final String COL_AGREE = "agree";
}
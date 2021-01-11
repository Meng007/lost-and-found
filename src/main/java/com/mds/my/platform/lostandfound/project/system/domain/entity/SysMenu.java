package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mds.my.platform.lostandfound.common.web.Result;
import lombok.Data;

/**
 * @author 13557
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu {
    /**
     * 菜单id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 父级菜单
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 菜单排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 是否外链  1：是，0：否
     */
    @TableField(value = "is_frame")
    private Integer isFrame;

    /**
     * 菜单类型 1：目录，2：菜单 3：按钮
     */
    @TableField(value = "menu_type")
    private Integer menuType;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 是否隐藏 1：隐藏 0：显现
     */
    @TableField(value = "visible")
    private Integer visible;

    /**
     * 状态 1：停用，0正常
     */
    @TableField(value = "status")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_MENU_NAME = "menu_name";

    public static final String COL_PID = "pid";

    public static final String COL_ORDER = "order";

    public static final String COL_PATH = "path";

    public static final String COL_COMPONENT = "component";

    public static final String COL_IS_FRAME = "is_frame";

    public static final String COL_MENU_TYPE = "menu_type";

    public static final String COL_PERMS = "perms";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_USERE_ID = "usere_id";

    public static final String COL_ICON = "icon";

    public static final String COL_VISIBLE = "visible";

    public static final String COL_STATUS = "status";


}
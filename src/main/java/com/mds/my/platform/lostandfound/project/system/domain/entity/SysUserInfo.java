package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
    * 用户详情表
 * @author 13557
 */
@Data
@TableName(value = "sys_user_info")
public class SysUserInfo {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * qq
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 微信
     */
    @TableField(value = "weixin")
    private String weixin;

    /**
     * 宿舍地址
     */
    @TableField(value = "dormitory")
    private String dormitory;

    /**
     * 介绍
     */
    @TableField(value = "introduce")
    private String introduce;

    /**
     * 最后一次重置密码时间
     */
    @TableField(value = "last_reset")
    private Date lastReset;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "last_login")
    private Date lastLogin;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     *  手机号
     */
    @TableField(value = "phone")
    private String phone;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SCHOOL = "school";

    public static final String COL_EMAIL = "email";

    public static final String COL_QQ = "qq";

    public static final String COL_WEIXIN = "weixin";

    public static final String COL_DORMITORY = "dormitory";

    public static final String COL_INTRODUCE = "introduce";
}
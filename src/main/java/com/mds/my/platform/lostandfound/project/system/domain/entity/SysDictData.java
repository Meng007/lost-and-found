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
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict_data")
public class SysDictData {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 字典排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 字典标签
     */
    @TableField(value = "dict_label")
    private String dictLabel;

    /**
     * 字典值
     */
    @TableField(value = "dict_value")
    private String dictValue;

    /**
     * 字典数据状态 1：停用，0：可用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    private Integer dictType;

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
    private Date updateTime;

    /**
     * 创建用户
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 更新用户
     */
    @TableField(value = "update_user")
    private Integer updateUser;

    public static final String COL_ID = "id";

    public static final String COL_SORT = "sort";

    public static final String COL_DICT_LABEL = "dict_label";

    public static final String COL_DICT_VALUE = "dict_value";

    public static final String COL_STATUS = "status";

    public static final String COL_DICT_TYPE = "dict_type";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";
}
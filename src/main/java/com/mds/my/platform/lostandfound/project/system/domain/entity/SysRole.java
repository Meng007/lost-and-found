package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 13557
 */
@Data
@TableName(value = "sys_role")
public class SysRole {
    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 角色排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 角色状态 1：禁用，0：可用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 逻辑删除 1：是，0：否
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_KEY = "role_key";

    public static final String COL_SORT = "sort";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETE = "is_delete";
}
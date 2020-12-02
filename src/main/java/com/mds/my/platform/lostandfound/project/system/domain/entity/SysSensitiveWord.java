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
@TableName(value = "sys_sensitive_word")
public class SysSensitiveWord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 敏感词类型
     */
    @TableField(value = "sensitive_type")
    private Integer sensitiveType;

    /**
     * 敏感词
     */
    @TableField(value = "sensitive_words")
    private String sensitiveWords;

    /**
     * 状态：1 启用、0 禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private Integer updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 敏感话题
     */
    @TableField(value = "sensitiveTopic")
    private String sensitivetopic;

    public static final String COL_ID = "id";

    public static final String COL_SENSITIVE_TYPE = "sensitive_type";

    public static final String COL_SENSITIVE_WORDS = "sensitive_words";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_SENSITIVETOPIC = "sensitiveTopic";
}
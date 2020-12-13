package com.mds.my.platform.lostandfound.project.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 物品表
 * @author mds
 */
@Data
@TableName(value = "sys_goods")
public class SysGoods {
    /**
     * 物品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 物品标题
     */
    @TableField(value = "goods_title")
    private String goodsTitle;

    /**
     * 物品内容描述
     */
    @TableField(value = "goods_content")
    private String goodsContent;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 丢失时间
     */
    @TableField(value = "lose_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date loseTime;

    /**
     * 封面图
     */
    @TableField(value = "cover_image")
    private String coverImage;

    /**
     * 状态： 0： 待审核，1：通过，2：禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 物品类型
     */
    @TableField(value = "goods_type")
    private Integer goodsType;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 留言量
     */
    @TableField(value = "message_num")
    private Integer messageNum;

    /**
     * 发布人id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 逻辑删除： 1：删除，0：存在
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 是否置顶
     */
    @TableField(value = "topping")
    private Boolean topping;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 联系人
     */
    @TableField(value = "linkman")
    private String linkman;

    /**
     * 物品状态 1：丢失，2；发布
     */
    @TableField(value = "goods_status")
    private Integer goodsStatus;
    /**
     * 物品 地址
     */
    @TableField(value = "address")
    private String address;

    public static final String COL_ID = "id";

    public static final String COL_GOODS_NAME = "goods_name";

    public static final String COL_GOODS_TITLE = "goods_title";

    public static final String COL_GOODS_CONTENT = "goods_content";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LOSE_TIME = "lose_time";

    public static final String COL_COVER_IMAGE = "cover_image";

    public static final String COL_STATUS = "status";

    public static final String COL_GOODS_TYPE = "goods_type";

    public static final String COL_VIEWS = "views";

    public static final String COL_MESSAGE_NUM = "message_num";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_TOPPING = "topping";

    public static final String COL_PHONE = "phone";

    public static final String COL_LINKMAN = "linkman";
    public static final String COL_GOODS_STATUS = "goods_status";
}
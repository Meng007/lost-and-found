package com.mds.my.platform.lostandfound.common.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    /**
     * 角色分配
     */
    ADMIN(1,"管理员"),
    STUDENT(2,"学生"),
    TOURIST(3,"游客");
    private Integer code;
    private String info;
    RoleEnum(Integer code,String info){
        this.code = code;
        this.info = info;
    }
}

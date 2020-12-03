package com.mds.my.platform.lostandfound.common.enums;

/**
 * @author 13557
 */

public enum UserStatus {
    /**
     *
     */
    OK(1, "正常"),
    DISABLE(0, "停用"),
    DELETED(1, "删除"),
    USER_TYPE(1, "管理员");

    private final Integer code;
    private final String info;

    UserStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}

package com.mds.my.platform.lostandfound.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {

    /**
     *
     */
    NO_ID(5001,"id为空"),
    NO_GOODS(5002,"物品不存在"),
    ;
    private Integer code;
    private String msg;
    ResultCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}

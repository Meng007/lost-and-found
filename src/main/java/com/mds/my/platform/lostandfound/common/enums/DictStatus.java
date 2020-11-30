package com.mds.my.platform.lostandfound.common.enums;

import lombok.Getter;

@Getter
public enum DictStatus {

    OPEN(0,"可用"),
    CLOSE(1,"禁用");


    private Integer code;
    private String info;
    DictStatus(Integer code,String info){
        this.code = code;
        this.info = info;
    }
}

package com.mds.my.platform.lostandfound.common.enums;

import lombok.Getter;

/**
 * @author 13557
 */

@Getter
public enum GoodsStatus {
    /**
     *
     */
    GOODS_LOST(1,"丢失"),
    PASS(1,"通过"),
    NO(2,"禁用"),
    GOODS_TAKE(0,"拾到"),
    WAIT(0,"待审核"),
   ;

    private Integer code;
    private String info;
    GoodsStatus(Integer code,String info){
        this.code = code;
        this.info = info;
    }
}

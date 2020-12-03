package com.mds.my.platform.lostandfound.common.web;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 13557
 */
@Data
@Builder
public class PageResult<T> {
    private Long total;
    private String msg;
    private Integer code;
    private List<T> data;
}

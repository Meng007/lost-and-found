package com.mds.my.platform.lostandfound.common.utils;

import com.github.pagehelper.PageHelper;
import com.mds.my.platform.lostandfound.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class StartPageUtils {

    public static void startPage(Map<String,Object> params){
        String page = (String)params.get("page");
        String size = (String)params.get("size");
        Integer p = 1;
        Integer s = 10;
        try {
            if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)){
                p = Integer.parseInt(page);
                s = Integer.parseInt(size);
            }
        }catch (Exception e){
            throw new BaseException("参数解析错误，请勿修改参数！");
        }
        PageHelper.startPage(p,s);
    }
}

package com.mds.my.platform.lostandfound.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T>{
    private Integer code;
    private String msg;
    private T data;

    /**
     *  响应结果
     * @param code 响应状态码
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(Integer code,String msg,T data){
        return new Result<T>(code,msg,data);
    }

    /**
     *
     *
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg,T data){
        return success(200, msg, data);
    }

    /**
     *
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg){
        return success(200, msg,null);
    }

    /**
     *
     * @param code 响应状态码
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(Integer code,String msg,T data){
        return new Result<>(code,msg,data);
    }

    /**
     *
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String msg,T data){
        return fail(500,msg,data);
    }

    /**
     *
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String msg){
        return fail(500,msg,null);
    }

    public static <T> Result<T> fail(Integer code,String msg){
        return fail(code,msg,null);
    }


}

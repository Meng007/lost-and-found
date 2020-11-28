package com.mds.my.platform.lostandfound.framework.aspectj.lang;

import java.lang.annotation.*;

/** 日志注解
 * @author mds
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}

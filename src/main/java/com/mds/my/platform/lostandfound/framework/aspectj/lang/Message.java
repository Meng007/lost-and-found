package com.mds.my.platform.lostandfound.framework.aspectj.lang;

import java.lang.annotation.*;

/**
 * @author mds
 * 消息通知
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Message {
    String type() default "";
}

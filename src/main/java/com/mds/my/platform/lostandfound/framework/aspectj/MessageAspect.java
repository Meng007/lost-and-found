package com.mds.my.platform.lostandfound.framework.aspectj;

import com.mds.my.platform.lostandfound.framework.aspectj.lang.Message;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * 消息切面
 * @author mds
 */
@Aspect
@Component
public class MessageAspect {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.mds.my.platform.lostandfound.framework.aspectj.lang.Message)")
    public void messagePointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint  切点
     * @param jsonResult 参数
     */
    @AfterReturning(pointcut = "messagePointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleMessage(joinPoint, null, jsonResult);
    }

    /**
     *  处理数据
     * @param joinPoint
     * @param o
     * @param jsonResult
     */
    private void handleMessage(JoinPoint joinPoint, Object o, Object jsonResult) {
        //获取注解
        Message message = getAnnotation(joinPoint);
        if (Objects.isNull(message)){
            return ;
        }
        //创建消息对象
        SysMessage sysMessage = new SysMessage();
        //给消息对象赋值
        setMessage(sysMessage,joinPoint);

    }

    /**
     * 给消息对象赋值
     * @param sysMessage
     * @param joinPoint
     */
    private void setMessage(SysMessage sysMessage, JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        Parameter[] parameters = method.getParameters();

    }

    /**
     * 获取注解
     * @param joinPoint
     * @return
     */
    private Message getAnnotation(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        if (!Objects.isNull(method)){
            return method.getAnnotation(Message.class);
        }
        return null;
    }

    /**
     *  获取注解的反射方法
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint){
        //获取签名
        Signature signature = joinPoint.getSignature();
        //获取子实现类
        MethodSignature methodSignature = (MethodSignature)signature;
        //获取注解上的方法
        Method method = methodSignature.getMethod();
        return method;
    }

}

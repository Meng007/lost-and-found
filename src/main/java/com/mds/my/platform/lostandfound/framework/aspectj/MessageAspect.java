package com.mds.my.platform.lostandfound.framework.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.SpringBeanUtils;
import com.mds.my.platform.lostandfound.framework.aspectj.lang.Message;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysGoodsMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMessageMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysGoodsService;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * 消息切面
 * @author mds
 */
@Aspect
@Component
@Data
public class MessageAspect {
    @Value("mds.goods.message.url")
    private String url = "http://127.0.0.1:8080/goods/info?goodsId=";
    @Autowired
    TokenService tokenService;
    @Autowired
    SysGoodsMapper sysGoodsMapper;
    @Autowired
    GoodsMessageMapper goodsMessageMapper;
    @Autowired
    SysMessageMapper sysMessageMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
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
        CompletableFuture.runAsync(() ->{
            //handleMessage(joinPoint, null, jsonResult);
        });

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
        System.out.println("============消息切面开始=================");
        //创建消息对象
        SysMessage sysMessage = new SysMessage();
        //给消息对象赋值
        setMessage(sysMessage,joinPoint);
        System.out.println("====================消息切面结束=======================");
    }

    /**
     * 给消息对象赋值
     * @param sysMessage
     * @param joinPoint
     */
    private void setMessage(SysMessage sysMessage, JoinPoint joinPoint) {
        Message message = getAnnotation(joinPoint);
        String type = message.type();
        System.out.println("======type======");
        System.out.println(type);
        if (StringUtils.isEmpty(type)){
            System.out.println("kong");
            return;
        }
        sysMessage.setType(type.trim());
        System.out.println("--------进坑----------");
        switch (type){
            //物品留言
            case "goodsMessage": saveGoodsMessage(sysMessage);
            break;
            //留言
            case "comment": saveComment(sysMessage);
            break;
            //系统消息
            case"system": saveSystemMessage(sysMessage);
            break;
            //留言点赞
            case "agree": saveAgreeMessage(sysMessage);
            break;
            default:
                System.out.println("无操作");
        }

    }

    private void saveAgreeMessage(SysMessage sysMessage) {
    }

    private void saveSystemMessage(SysMessage sysMessage) {
    }

    private void saveComment(SysMessage sysMessage) {
    }

    /**
     * 保存物品留言消息
     * @param sysMessage
     */
    private void saveGoodsMessage(SysMessage sysMessage) {
        System.out.println("===========开始入口===========");
        //获取请求消息
        //HttpServletRequest request = ServletUtils.getRequest();
        try {
            System.out.println("请求对象："+httpServletRequest.getParameter("messageContent"));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===========开始结束===========");
        //请求的参数
        GoodsMessage goodsMessage = null;
        System.out.println("===========创建消息===========");
        try {
            goodsMessage = getRequestBean(httpServletRequest, GoodsMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //消息内容
        String content = "";
        //获取物品消息
        if (Objects.isNull(goodsMessage)){
            return;
        }
        //获取留言物品
        SysGoods sysGoods = sysGoodsMapper.selectById(goodsMessage.getGoodsId());
        //赋值
        //创建时间
        sysMessage.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //创建用户，消息的发起者
        sysMessage.setCreateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        //设置 已读(0) 未读(1) 状态
        sysMessage.setStatus(1);
        //设置删除状态 删除(1) 未删除(0)
        sysMessage.setIsDelete(0);
        System.out.println("=========准备发送消息===========");
        if (goodsMessage.getMessageId() ==null || goodsMessage.getMessageId() ==0){
            content = "你的发布的物品【"+sysGoods.getGoodsTitle()+"】被["+tokenService.getLoginUser(httpServletRequest).getUsername()+"]留言，点击[<a href='"+ url+goodsMessage.getGoodsId()+"'>我要查看看</a>]即可查看";
            //消息内容
            sysMessage.setMessage(content);
            //接受用户
            sysMessage.setUserId(sysGoods.getUserId());
        }else {
            //获取留言的消息
            GoodsMessage goodsMessage1 = goodsMessageMapper.selectById(goodsMessage.getMessageId());
            content = "你在物品【"+sysGoods.getGoodsTitle()+"】的留言被["+tokenService.getLoginUser(httpServletRequest).getUsername()+"]用户回复，点击[<a href='"+ url+goodsMessage.getGoodsId()+"'>我要查看看</a>]即可查看";
            //消息内容
            sysMessage.setMessage(content);
            //接受用户
            sysMessage.setUserId(goodsMessage1.getUserId());
        }
        System.out.println("==========发送消息=======");
        //保存消息
        sysMessageMapper.insert(sysMessage);
    }

    private <T> T getRequestBean(HttpServletRequest request, Class<T> cls) throws IOException {
        @Cleanup
        InputStream in = null;
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
             in = request.getInputStream();
            int len = 0;
            while ((len = in.read(bytes,0,bytes.length)) !=-1){
                buffer.append(new String(buffer));
            }
        if (buffer.toString().length()>0){
            T t = JSONObject.parseObject(buffer.toString(), cls);
            return t;
        }
        return null;
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

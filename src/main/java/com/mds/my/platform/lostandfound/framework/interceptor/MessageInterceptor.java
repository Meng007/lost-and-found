package com.mds.my.platform.lostandfound.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mds.my.platform.lostandfound.common.utils.HttpHelper;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.framework.aspectj.lang.Message;
import com.mds.my.platform.lostandfound.framework.filter.RepeatedlyRequestWrapper;
import com.mds.my.platform.lostandfound.framework.interceptor.annotation.RepeatSubmit;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysGoodsMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMessageMapper;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Objects;

@Component
public class MessageInterceptor extends HandlerInterceptorAdapter {

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                Message annotation = method.getAnnotation(Message.class);
                if (!Objects.isNull(annotation)) {
                    RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
                    String nowParams = HttpHelper.getBodyString(repeatedlyRequest);

                    // body参数为空，获取Parameter的数据
                    if (StringUtils.isEmpty(nowParams)) {
                        System.out.println("AK47");
                        nowParams = JSONObject.toJSONString(request.getParameterMap());
                    }
                    System.out.println("数据：" + nowParams);
                    handleMessage(repeatedlyRequest, annotation);
                }
            }
        return super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 处理数据
     *
     * @param request
     * @param annotation
     */
    private void handleMessage(HttpServletRequest request, Message annotation) {
        //创建消息对象
        SysMessage sysMessage = new SysMessage();
        //给消息对象赋值
        setMessage(sysMessage, request, annotation);
    }

    /**
     * 给消息对象赋值
     *
     * @param sysMessage
     * @param annotation
     */
    private void setMessage(SysMessage sysMessage, HttpServletRequest request, Message annotation) {
        String type = annotation.type();
        System.out.println(type);
        if (StringUtils.isEmpty(type)) {
            return;
        }
        sysMessage.setType(type.trim());
        switch (type) {
            //物品留言
            case "goodsMessage":
                saveGoodsMessage(sysMessage, request);
                break;
            //留言
            case "comment":
                saveComment(sysMessage, request);
                break;
            //系统消息
            case "system":
                saveSystemMessage(sysMessage, request);
                break;
            //留言点赞
            case "agree":
                saveAgreeMessage(sysMessage, request);
                break;
            default:
                System.out.println("无操作");
        }

    }

    private void saveSystemMessage(SysMessage sysMessage, HttpServletRequest request) {
    }

    private void saveComment(SysMessage sysMessage, HttpServletRequest request) {
    }

    private void saveAgreeMessage(SysMessage sysMessage, HttpServletRequest request) {
    }

    /**
     * 保存物品留言消息
     *
     * @param sysMessage
     */
    private void saveGoodsMessage(SysMessage sysMessage, HttpServletRequest request) {
        //获取请求消息
        RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
        String nowParams = HttpHelper.getBodyString(repeatedlyRequest);
        // body参数为空，获取Parameter的数据
        if (StringUtils.isEmpty(nowParams)) {
            System.out.println("AK47");
            nowParams = JSONObject.toJSONString(request.getParameterMap());
        }
        //请求的参数
        GoodsMessage goodsMessage = null;
        try {
            goodsMessage = getRequestBean(nowParams, GoodsMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //消息内容
        String content = "";
        //获取物品消息
        if (Objects.isNull(goodsMessage)) {
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
        if (goodsMessage.getMessageId() == null || goodsMessage.getMessageId() == 0) {
            content = "你的发布的物品【" + sysGoods.getGoodsTitle() + "】被[" + tokenService.getLoginUser(httpServletRequest).getUsername() + "]留言，点击[<a href='" + url + goodsMessage.getGoodsId() + "'>我要查看看</a>]即可查看";
            //消息内容
            sysMessage.setMessage(content);
            //接受用户
            sysMessage.setUserId(sysGoods.getUserId());
        } else {
            //获取留言的消息
            GoodsMessage goodsMessage1 = goodsMessageMapper.selectById(goodsMessage.getMessageId());
            content = "你在物品【" + sysGoods.getGoodsTitle() + "】的留言被[" + tokenService.getLoginUser(httpServletRequest).getUsername() + "]用户回复，点击[<a href='" + url + goodsMessage.getGoodsId() + "'>我要查看看</a>]即可查看";
            //消息内容
            sysMessage.setMessage(content);
            //接受用户
            sysMessage.setUserId(goodsMessage1.getUserId());
        }
        System.out.println("==========发送消息=======");
        //保存消息
        sysMessageMapper.insert(sysMessage);
    }

    private <T> T getRequestBean(String nowParams, Class<T> cls) throws IOException {
        if (StringUtils.isNotEmpty(nowParams)) {
            T t = JSONObject.parseObject(nowParams, cls);
            return t;
        }
        return null;
    }

}

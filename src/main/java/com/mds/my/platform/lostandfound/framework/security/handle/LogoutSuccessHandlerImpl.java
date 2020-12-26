package com.mds.my.platform.lostandfound.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.LoginUser;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //从redis获取当前登录用户
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (!Objects.isNull(loginUser)){
            //从redis中删除用户
            tokenService.delLoginUser(loginUser.getToken());
        }

        ServletUtils.renderString(response, JSON.toJSONString(Result.success(HttpStatus.OK.value(), "退出成功",null)));
    }
}

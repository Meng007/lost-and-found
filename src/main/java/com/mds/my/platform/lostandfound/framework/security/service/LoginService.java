package com.mds.my.platform.lostandfound.framework.security.service;

import com.mds.my.platform.lostandfound.common.exception.BaseException;
import com.mds.my.platform.lostandfound.framework.redis.RedisService;
import com.mds.my.platform.lostandfound.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    public String login(String username, String password){
        Authentication authentication = null;
        try{
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            if (e instanceof BadCredentialsException){
                //密码错误
                //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new BaseException("密码错误！");
            }else{
                //用户名错误
                //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new BaseException(e.getMessage());
            }
        }
        //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println("登录成功的用户："+loginUser);
        // 生成token
        return tokenService.createToken(loginUser);
    }
}

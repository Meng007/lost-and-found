package com.mds.my.platform.lostandfound.framework.filter;


import com.mds.my.platform.lostandfound.common.utils.HttpHelper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 构建可重复读取inputStream的request
 *
 * @author ruoyi
 */
public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper {
    //把请求的输入流保存起来，每次都可以获取到
    private final byte[] body;

    /**
     *  构造器
     * @param request 请求
     * @param response 响应
     * @throws IOException io异常
     */
    public RepeatedlyRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException {
        super(request);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        body = HttpHelper.getBodyString(request).getBytes("UTF-8");
    }

    /**
     *  获取缓冲流
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 获取输入流
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //获取字节输入流
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        //修改获取输入流的来源
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}
        };
    }
}

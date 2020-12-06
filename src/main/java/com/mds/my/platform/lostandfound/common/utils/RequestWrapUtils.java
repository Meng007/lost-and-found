package com.mds.my.platform.lostandfound.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class RequestWrapUtils<T> {

    public static <T> T getBean(Class<T> cls, HttpServletRequest request){
        try {
            T obj = cls.getDeclaredConstructor().newInstance();
            //获取参数  ?title=aaaa&content=xxxxxx&xx=yyyy
            Map<String, String[]> paramMap =  request.getParameterMap();
            //遍历Map
            Set<Map.Entry<String, String[]>> set = paramMap.entrySet();

            for (Map.Entry<String, String[]> entry : set) {
                //属性名
                String name = entry.getKey();
                String[] value = entry.getValue();
                try {
                    //根据属性名获取setter方法  title----setTitle()
                    //setName = "setTitle";
                    String setName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
                    //获取对应的属性的类型
                    Class<?> type = cls.getDeclaredField(name).getType();
                    // 方法名  参数类型
                    Method method = cls.getDeclaredMethod(setName, type);
                    Object val = value[0];
                    // 转型问题：
                    if(type==int.class || type==Integer.class) {
                        val = Integer.parseInt(val.toString());
                    }else if(type==float.class || type==Float.class) {
                        val = Float.parseFloat(val.toString());
                    }
                    //obj.setTitle(value);
                    method.invoke(obj, val);
                }catch (Exception e) {

                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

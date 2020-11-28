package com.mds.my.platform.lostandfound.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 获取spring bean
 * @author mds
 */
public class SpringBeanUtils implements BeanFactoryPostProcessor {

    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringBeanUtils.beanFactory = configurableListableBeanFactory;
    }

    /**
     * 一个以所给名字注册的bean的实例
     * @param name
     * @param name
     * @return Obj 一个以所给名字注册的bean的实例
     */
    public <T> T getBean(String name) throws BeansException{
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }

}

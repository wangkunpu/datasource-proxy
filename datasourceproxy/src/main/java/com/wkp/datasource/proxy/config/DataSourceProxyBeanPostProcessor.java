package com.wkp.datasource.proxy.config;

import com.wkp.datasource.proxy.handler.DataSourceInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;

public class DataSourceProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, String s) throws BeansException {
        if (o instanceof DataSource) {
            return Proxy.newProxyInstance(DataSourceProxyBeanPostProcessor.class.getClassLoader(),
                    new Class[]{DataSource.class}, new DataSourceInvocationHandler(o));
        }
        return o;
    }
}

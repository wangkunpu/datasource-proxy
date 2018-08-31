package com.wkp.datasource.proxy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class StatementInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementInvocationHandler.class);
    private Object target;
    private Object[] params;

    public StatementInvocationHandler() {
    }

    public StatementInvocationHandler(Object target, Object[] params) {
        this.target = target;
        this.params = params;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("execute")) {
            String sql = (String) args[0];
            LOGGER.info("当前sql语句为：{}", sql);
            Long startTime = new Date().getTime();
            Object result = method.invoke(target, args);
            LOGGER.info("当前sql执行时间为：{} ms", new Date().getTime() - startTime);
        }
        return method.invoke(target, args);
    }
}

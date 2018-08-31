package com.wkp.datasource.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class DataSourceInvocationHandler implements InvocationHandler {
    private Object target;

    public DataSourceInvocationHandler() {
    }

    public DataSourceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        if (method.getName().equals("getConnection")) {
            return Proxy.newProxyInstance(DataSourceInvocationHandler.class.getClassLoader()
                    , new Class[]{Connection.class}, new ConnectionInvocationHandler(result, args));
        }
        return result;
    }
}

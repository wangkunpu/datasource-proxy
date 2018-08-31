package com.wkp.datasource.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionInvocationHandler implements InvocationHandler {
    private Object target;
    private Object[] params;

    public ConnectionInvocationHandler() {
    }

    public ConnectionInvocationHandler(Object target, Object[] params) {
        this.target = target;
        this.params = params;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        if (method.getName().equals("createStatement")) {
            return Proxy.newProxyInstance(ConnectionInvocationHandler.class.getClassLoader(),
                    new Class[]{Statement.class}, new StatementInvocationHandler(result, args));
        }
        if (method.getName().equals("prepareStatement")) {
            return Proxy.newProxyInstance(ConnectionInvocationHandler.class.getClassLoader(),
                    new Class[]{PreparedStatement.class}, new PreparedStatementInvocationHandler(result, args));
        }
        return result;
    }

}

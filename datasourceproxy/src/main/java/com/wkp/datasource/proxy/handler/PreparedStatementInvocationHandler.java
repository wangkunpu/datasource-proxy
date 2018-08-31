package com.wkp.datasource.proxy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class PreparedStatementInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedStatementInvocationHandler.class);

    private Object target;
    private Object[] params;
    private Map<Integer, Object> argMap = new TreeMap<>();

    public PreparedStatementInvocationHandler() {
    }


    public PreparedStatementInvocationHandler(Object target, Object[] params) {
        this.target = target;
        this.params = params;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = (String) params[0];
        if (method.getName().startsWith("execute")) {
            LOGGER.info("当前sql语句为：{}", sql);
            LOGGER.info("当前sql参数为：{}", getArgs());
            Long startTime = new Date().getTime();
            Object result = method.invoke(target, args);
            LOGGER.info("sql执行时长为：{} ms", new Date().getTime() - startTime);
            return result;
        }
        if (method.getName().startsWith("set")) {
            argMap.put((Integer) args[0], args[1]);
        }
        return method.invoke(target, args);
    }

    private String getArgs() {
        String str = "";
        for (Object o : argMap.values()) {
            String s = o.toString();
            if (!str.equals("")) {
                str += ",";
            }
            str += s;
        }
        return str;
    }
}

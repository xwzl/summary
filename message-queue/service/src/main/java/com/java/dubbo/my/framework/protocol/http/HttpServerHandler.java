package com.java.dubbo.my.framework.protocol.http;


import com.alibaba.fastjson.JSON;
import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import com.java.dubbo.my.framework.register.LocalRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Servlet 拦截器处理器，处理实际业务
 *
 * @author xuweizhi
 * @since 2021/05/25 15:17
 */
@Slf4j
@SuppressWarnings("all")
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            // JDK11之前用
            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
            Invocation invocation = (Invocation) ois.readObject();
            // 具体服务接口名称
            String interfaceName = invocation.getInterfaceName();
            // 通过接口名称获取具体实现
            Class<?> implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            Object result = method.invoke(implClass.newInstance(), invocation.getParams());
            log.info("tomcat:{}", result);
            IOUtils.write(JSON.toJSONString(result), resp.getOutputStream());
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

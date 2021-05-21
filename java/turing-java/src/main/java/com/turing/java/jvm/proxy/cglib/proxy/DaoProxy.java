package com.turing.java.jvm.proxy.cglib.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 创建一个Dao代理
 *
 * @author xuweizhi
 */
public class DaoProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("begin intercept");
        // invokeSuper方法调用目标类的方法
        proxy.invokeSuper(obj, args);
        System.out.println("end intercept");
        return obj;
    }
}

package com.spring.cloud.gateway.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xuweizhi
 * @since 2021/10/23 21:29
 */
@SuppressWarnings("all")
public class ProxyCreate {

    public static void main(String[] args) throws NoSuchMethodException {
        SymbolInterface symbolInterface = (SymbolInterface) ProxyCreate.createObject(SymbolInterface.class.getName() + "@getName=123");
        System.out.println(symbolInterface.getName());
        System.out.println(symbolInterface.getClass());
        symbolInterface = (SymbolInterface) ProxyCreate.createObject(SymbolInterface.class.getName() + "@getName1=123");
        System.out.println(symbolInterface.getClass());
        System.out.println(symbolInterface.getName());
        Map<String,String> map = new HashMap<>(2);
    }

    public static Object createObject(String str) {
        if (str == null || !str.contains("@")) {
            throw new IllegalArgumentException("入参异常，请重新输入");
        }
        String[] classInfo = str.split("@");
        Object result = null;
        try {
            Class<?> clazz = Class.forName(classInfo[0]);
            result = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (proxy instanceof SymbolInterface) {
                        String methodInfo = classInfo[1];
                        if (methodInfo.contains("=")) {
                            String[] methodBefinition = methodInfo.split("=");
                            try {
                                Method method1 = clazz.getMethod(methodBefinition[0], null);
                            } catch (NoSuchMethodException | SecurityException e) {
                                return null;
                            }
                            return methodBefinition[1];
                        }
                    }
                    return null;
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static interface SymbolInterface {
        String getName();
    }
}

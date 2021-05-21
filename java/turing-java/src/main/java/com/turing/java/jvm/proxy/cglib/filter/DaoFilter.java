package com.turing.java.jvm.proxy.cglib.filter;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 返回数值表示顺序
 */
public class DaoFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        System.out.println("accept");
        if ("select".equalsIgnoreCase(method.getName())) {
            return 0;
        } else if ("delete".equalsIgnoreCase(method.getName())) {
            return 1;
        }
        return 2;
    }
}

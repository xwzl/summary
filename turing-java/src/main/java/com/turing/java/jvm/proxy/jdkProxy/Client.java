package com.turing.java.jvm.proxy.jdkProxy;

import com.turing.java.jvm.proxy.cglib.mapper.Dao;
import com.turing.java.jvm.proxy.cglib.mapper.IDao;
import com.turing.java.jvm.proxy.jdkProxy.proxy.DaoJdkProxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IDao dao = (IDao)Proxy.newProxyInstance(Dao.class.getClassLoader(), Dao.class.getInterfaces(),
            new DaoJdkProxy(new Dao()));
        dao.select();
    }
}

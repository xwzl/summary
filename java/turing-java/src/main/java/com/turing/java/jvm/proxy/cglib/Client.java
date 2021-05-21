package com.turing.java.jvm.proxy.cglib;

import com.turing.java.jvm.proxy.cglib.mapper.Dao;
import com.turing.java.jvm.proxy.cglib.proxy.DaoProxy;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {

        // 将代理类存到本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
        // 实例化增强器
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的目标类
        enhancer.setSuperclass(Dao.class);
        // 设置拦截对象 回调的实现类
        enhancer.setCallback(new DaoProxy());
        // 使用create 返回Object 生成代理类并返回实例
        Dao dao = (Dao)enhancer.create();
        // select优先级高 使用DaoProxy
        dao.select();
        // 无法代理被final修饰的方法
        // dao.delete();
        // dao.insert();

    }
}

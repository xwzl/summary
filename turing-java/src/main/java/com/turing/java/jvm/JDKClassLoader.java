package com.turing.java.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * <h2>ClassLoader 双亲委派机制</h2>
 * <h3>各类类加载器初始化时机</h3>
 * <p>
 * {@link sun.misc.Launcher#getLauncher()} jdk 1.8 有效
 *
 * <p>
 * 双亲委派机制主要依赖于 {@link ClassLoader#loadClass(String)} 方法实现：
 *
 * 如果当前类加载器不是 BootstrapClassLoader 加载，依次委托给父类加载器.
 *
 * {@link ClassLoader#findClass(String)} 进行真正的类加载。
 *
 *
 * @author xuweizhi
 * @since 2020/08/03 11:27
 */
public class JDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(JDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}

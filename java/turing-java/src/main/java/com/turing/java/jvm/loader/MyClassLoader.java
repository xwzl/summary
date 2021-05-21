package com.turing.java.jvm.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * -XX:+TraceClassUnloading
 *
 * @author xuweizhi
 * @since 2019/02/21 16:42
 */
@Slf4j
public class MyClassLoader extends ClassLoader {


    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public MyClassLoader(String classLoaderName) {
        //将系统类加载器当做该类加载器的父类加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 类加载器是包含关系，不是继承关系，因为ClassLoader类中有一个parent变量，对子加载器的引用
     */
    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        //显示指定该类加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    /**
     * 必须定义这个方法，用于双亲委托机制
     */
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }


    /**
     * 没有被执行，因为加载器是由系统类加载器加载的，双亲委托机制让类加载器从上到下依次执行
     * 想要被执行，删除classPath下的文件且重新制定位置
     * <p>
     * loadClass 最终会调用该接口
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        log.info(name);
        log.info("loaderName: " + this.classLoaderName);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        className = className.replace(".", "/");
        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;

            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.turing.java.module.User");
        Object newInstance = clazz.getDeclaredConstructor().newInstance();
        log.info("{}", newInstance);
    }

    /**
     * 双亲委托机制示例*
     * 1. 如果是系统类加载器，根据命名空间规则文件只加载一次，且生成的Class对象为同一个对象，自定义的类加载方法不会被执行
     * 2. 如果是自定义加载器，根据命名空间规则文件文件会被重复加载，生成不同的Class对象实例，自定义的类加载方法会被执行
     * 命名空间
     * <li>
     * - 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成
     * - 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
     * - 在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类
     * </li>
     * <p>
     */
    public static void main(String[] args) throws Exception {
        // 加载非classPath路径的class文件，终于加载了自定义加载器，前提是删除classPath路径下对应的文件
        // 删除该项目下对应的 class 文件，会出现不同的信息
        String path = "C:\\Users\\ahq\\Desktop\\";
        String name = "com.turing.java.module.Fly";
        String printInfo = "------------------------------";

        MyClassLoader loader = new MyClassLoader("loader");
        loader.setPath(path);
        Class<?> clazz = loader.loadClass(name);
        log.info("clazz:" + clazz.hashCode());
        Object o = clazz.getDeclaredConstructor().newInstance();
        String classInfo = "class instance hashcode :{},classLoader{}";
        ClassLoader classLoader1 = o.getClass().getClassLoader();
        log.info(classInfo, o, classLoader1);

        log.info(printInfo);

        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath(path);
        Class<?> clazz1 = loader1.loadClass(name);
        log.info("clazz1:" + clazz1.hashCode());
        Object o1 = clazz.getDeclaredConstructor().newInstance();
        MyClassLoader classLoader = (MyClassLoader)o1.getClass().getClassLoader();
        log.info(classInfo, o1, classLoader);
        log.info("loader 和 loader1 是不同的两个类加载器且他们各自加载了一次 Fly 对象，因此得到的 class hashcode 值不一样");
        log.info(printInfo);

        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader1.setPath(path);

        Class<?> clazz2 = loader2.loadClass(name);
        log.info("clazz2:" + clazz2.hashCode());
        Object o2 = clazz.getDeclaredConstructor().newInstance();
        ClassLoader classLoader2 = o2.getClass().getClassLoader();
        log.info(classInfo, o2, classLoader2);
        log.info("loader1 和 loader2 一样是因为 loader2 调用 loadClass() 方法，依次向上委托给父类加载器；");
        log.info("loader2 父类加载器 loader1 加载过 fly 对象，因此 loader2 与 loader1 获取的类 hashcode 值一样");
        log.info(printInfo);

        MyClassLoader loader3 = new MyClassLoader("loader3");
        loader3.setPath(path);

        Class<?> clazz3 = loader3.loadClass(name);
        log.info("clazz3:" + clazz3.hashCode());
        Object o3 = clazz.getDeclaredConstructor().newInstance();
        ClassLoader classLoader3 = o3.getClass().getClassLoader();
        log.info(classInfo, o3, classLoader3);

        log.info(printInfo);
        //test(loader);

        //模拟类被卸载
        loader = null;
        clazz = null;
        o = null;

        System.gc();

        Thread.sleep(5000);

        loader = new MyClassLoader("loader");
        loader.setPath(path);

        clazz = loader.loadClass(name);
        log.info("clazz:" + clazz.hashCode());
        o = clazz.getDeclaredConstructor().newInstance();
        ClassLoader classLoader4 = o.getClass().getClassLoader();
        log.info(classInfo, o, classLoader4);
        log.info("该处代码与 loader 和 loader1 的情况类似，由新的类加载器加载 Fly ，获取的类 hashcode 值不一样");
    }


}

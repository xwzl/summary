package com.turing.java.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * Class 文件加载：
 * <p>
 * JVM 会预加载一些类来提高启动速度，但是对于大部分类来说，在其主动使用时才会加载。
 *
 * @author xuweizhi
 * @since 2020/08/03 11:16
 */
@Slf4j
public class DynamicLoad {
    static {
        log.info("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new DynamicLoadA();
        log.info("*************load test************");
        DynamicLoadB dynamicLoadB = null;  //B不会加载，除非这里执行 new B()
        // 非主动使用
        //Thread.currentThread().getContextClassLoader().loadClass("com.java.prepare.until.java.jvm.com.turing.java.jvm.DynamicLoadB");
        // 主动使用
        Class.forName("com.java.prepare.until.java.jvm.com.turing.java.jvm.DynamicLoadB");
    }
}

@Slf4j
class DynamicLoadA {
    static {
        log.info("*************load A************");
    }

    public DynamicLoadA() {
        log.info("*************initial A************");
    }
}

@Slf4j
class DynamicLoadB {
    static {
        log.info("*************load B************");
    }

    public DynamicLoadB() {
        log.info("*************initial B************");
    }
}

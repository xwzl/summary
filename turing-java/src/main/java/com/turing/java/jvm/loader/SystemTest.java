package com.turing.java.jvm.loader;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2020/08/14 16:57
 */
@Slf4j
public class SystemTest {

    public static void main(String[] args) {
        // java -Dsun.boot.class.path=path 设置路径
        log.info(System.getProperty("sun.boot.class.path"));
        log.info(System.getProperty("java.ext.dirs"));
        log.info(System.getProperty("java.class.path"));
    }
}


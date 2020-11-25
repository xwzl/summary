package com.spring.zookeeper.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author xuweizhi
 * @since 2020/11/25 14:27
 */
public class ZookeeperUtils {

    public static String ip;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ZookeeperUtils.class.getResource("/zookeeper.properties").openStream());
            ip = (String) properties.get("ip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

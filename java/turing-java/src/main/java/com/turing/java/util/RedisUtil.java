package com.turing.java.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author xuweizhi
 * @since 2020/11/11 22:52
 */
public class RedisUtil {

    private static Properties properties;

    static {
        URL resource = RedisUtil.class.getResource("/redis-config.properties");
        properties = new Properties();
        try {
            properties.load(resource.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String redisHost() {

        return (String) properties.get("host");
    }

}

package com.message.queue.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author xuweizhi
 * @since 2020/11/25 14:27
 */
public class ContextUtil {

    public static String ip;

    public static Integer port;

    public static String username;

    public static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ContextUtil.class.getResource("/context.properties").openStream());
            ip = (String) properties.get("ip");
            port = Integer.parseInt((String) properties.get("port"));
            username = (String) properties.get("username");
            password = (String) properties.get("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

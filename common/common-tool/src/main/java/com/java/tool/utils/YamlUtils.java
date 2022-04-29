package com.java.tool.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * yaml 工具类
 *
 * @author xuweizhi
 * @since 2022/04/25 21:04
 */
public class YamlUtils {


    public static class YamlSingletonInstance {
        public static Yaml yaml = new Yaml();
    }

    public static Map<String, Object> loadProperties(String filePath) {
        Yaml yaml = YamlSingletonInstance.yaml;
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        return yaml.loadAs(inputStream, Map.class);
    }
}

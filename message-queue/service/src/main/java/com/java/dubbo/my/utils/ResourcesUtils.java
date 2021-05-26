package com.java.dubbo.my.utils;


import com.java.dubbo.my.framework.protocol.auto.AutoValue;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 解析 Resources 文件
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class ResourcesUtils {

    Map<String, List<String>> map = new HashMap<>();

    final static List<String> FILED_TYPE = new ArrayList<>();

    private static final Pattern PATTERN;

    static {
        String regex = "\\$\\{(.+?)\\}";
        PATTERN = Pattern.compile(regex);
    }

    static {
        FILED_TYPE.add("Date");
        FILED_TYPE.add("Map");
        FILED_TYPE.add("List");
        FILED_TYPE.add("Set");
        FILED_TYPE.add("Integer");
        FILED_TYPE.add("Char");
        FILED_TYPE.add("Double");
    }

    /**
     * 解析文件
     */
    public void parse(String path) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        assert in != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        for (String line; (line = reader.readLine()) != null; ) {
            int i = line.indexOf(".");
            int i1 = line.indexOf("=");
            if (StringUtils.isNotEmpty(line) && line.length() > 0) {
                List<String> list = map.computeIfAbsent(line.substring(0, i).toUpperCase(), k -> new ArrayList<>());
                list.add(line.substring(i + 1, i1).trim() + "#" + line.substring(i1 + 1).trim());
            }
        }
        in.close();
        reader.close();
    }


    public String resolver(Class<?> clazz, Field field, Object instance) {
        List<String> list = map.get(clazz.getSimpleName().toUpperCase());
        AutoValue autoValue = field.getAnnotation(AutoValue.class);
        String param = autoValue.value();
        if (checkFieldName(param)) {
            param = param.substring(param.indexOf("{") + 1, param.indexOf("}"));
        } else {
            throw new RuntimeException(clazz.getName() + "." + field.getName() + " syntax error，Please use ${" + field.getName() + "}");
        }
        if (list != null) {
            field.setAccessible(true);
            for (String value : list) {
                try {
                    int i = value.indexOf("#");
                    if(field.getType().getName().endsWith("Boolean")){
                        field.set(instance, Boolean.parseBoolean(value.substring(i+1)));
                        continue;
                    }
                    if (field.getType().getName().endsWith("Date")) {
                        field.set(instance, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.substring(i + 1, value.length())));
                        continue;
                    }
                    if (field.getType().getName().endsWith("Map")) {
                        //todo
                        continue;
                    }
                    if (field.getType().getName().endsWith("Set")) {
                        //todo
                        continue;
                    }
                    if (field.getType().getName().endsWith("List")) {
                        // todo
                        continue;
                    }
                    if (StringUtils.isNotEmpty(value.substring(i + 1))) {
                        field.set(instance, value.substring(i + 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public boolean checkFieldName(String value) {
        return PATTERN.matcher(value).find();
    }

    public String getFieldName(String value) {
        return value.substring(1, value.length() - 1);
    }

}

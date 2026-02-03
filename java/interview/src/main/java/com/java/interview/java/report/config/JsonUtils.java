package com.java.interview.java.report.config;

import com.java.interview.java.report.handler.AreaLog;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author xuweizhi
 * @since 2022/06/01 11:09
 */
public class JsonUtils {
    private final static ObjectMapper ob = new ObjectMapper();

    public static <T> T toJavaMap(Object obj, TypeReference<T> token) {
        if (Objects.isNull(obj)) {
            return null;
        }
            String json = ob.writeValueAsString(obj);
            return ob.readValue(json, token);
    }


    public static void main(String[] args)  {
        AreaLog areaLog = new AreaLog(1L, 2L, "11", "1");

        List<AreaLog> areaLogs = Arrays.asList(areaLog);
        Map<Long, AreaLog> collect = areaLogs.stream().collect(Collectors.toMap(AreaLog::getId, Function.identity()));
        Map<String, Map<String, Object>> stringMapMap = toJavaMap(collect, new TypeReference<Map<String, Map<String, Object>>>() {
        });
        System.out.println(areaLog);
    }
}

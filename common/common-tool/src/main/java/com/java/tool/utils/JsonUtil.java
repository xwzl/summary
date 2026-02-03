package com.java.tool.utils;

import org.apache.commons.lang3.StringUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON 转换工具
 *
 * @author xuweizhi
 * @since 2022/04/07 20:17
 */
public class JsonUtil {

    private final static JsonMapper mapper = new JsonMapper();

    public static <T> T fromJson(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return mapper.readValue(json, type);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return mapper.readValue(json, typeReference);
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyList();
        }
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, type));
    }

    public static <T> T[] fromJsonToArray(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return mapper.readValue(json, mapper.getTypeFactory().constructArrayType(type));
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyType, Class<V> valueType) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return mapper.readValue(json, mapper.getTypeFactory().constructMapType(HashMap.class, keyType, valueType));
    }

    public static JsonNode fromJson(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return mapper.readTree(json);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> String toJsonString(T object) {
        if (object == null) {
            return null;
        }
        return mapper.writeValueAsString(object);
    }
}

package com.java.tool.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tool.exceptions.JsonException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
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

    private final static ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T[] fromJsonToArray(String json, Class<T> type) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructArrayType(type));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyType, Class<V> valueType) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(HashMap.class, keyType, valueType));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode fromJson(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> String toJsonString(T object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
}

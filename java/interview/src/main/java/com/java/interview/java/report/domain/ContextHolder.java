package com.java.interview.java.report.domain;

import com.java.interview.java.report.resolve.Resolve;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author xuweizhi
 * @since 2022/05/30 16:41
 */
@Slf4j
public class ContextHolder {
    public static final ThreadLocal<Context> CACHE = new ThreadLocal<>();

    public static void setContextHolder(Context context) {
        if (Objects.nonNull(CACHE.get())) {
            throw new RuntimeException("流程初始化错误");
        }
        CACHE.set(context);
    }

    public static Context getContext() {
        return CACHE.get();
    }

    public static Object getDataSource(String key) {
        return CACHE.get().getDataSource(key);
    }

    public static Resolve getHandler(String key) {
        return CACHE.get().getHandler(key);
    }

    public static void setValue(String key, Object value) {
        Context context = CACHE.get();
        if (Objects.nonNull(context)) {
            context.putDataSource(key, value);
        } else {
            log.error("处理异常:key:{},value:{}", key, value);
        }
    }

    public static void remove() {
        CACHE.remove();
    }
}

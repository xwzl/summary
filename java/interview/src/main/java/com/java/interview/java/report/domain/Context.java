package com.java.interview.java.report.domain;

import com.java.interview.java.report.handler.Handler;
import com.java.interview.java.report.resolve.Resolve;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 16:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Context {

    private Map<String, Object> dataSource = new HashMap<>();
    private Map<String, Resolve> handlers = new HashMap<>();

    private TemplateConfig templateConfig;

    private Handler handler;

    private Map<String, Object> tempSource;

    private Boolean switchDoc;

    private Boolean switchLogMonitor;

    public <T> T getSource(Class<T> clazz) {
        Map<Object, T> objectObjectMap = (Map<Object, T>) dataSource.get(handler.getClass().getName());
        return objectObjectMap.get(Long.parseLong(tempSource.get(templateConfig.getMappingField()).toString()));
    }

    public void putDataSource(String handlerKey, Object value) {
        dataSource.put(handlerKey, value);
    }

    public Object getDataSource(String handlerKey) {
        return dataSource.get(handlerKey);
    }

    public Resolve getHandler(String handler) {
        return handlers.get(handler);
    }

    public void putHandler(String handlerKey, Resolve handler) {
        handlers.put(handlerKey, handler);
    }

}

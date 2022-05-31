package com.java.interview.java.report.domain;

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

    /**
     * List map
     */
    private Map<String, Object> dataSource = new HashMap<>();


    private Map<String, Resolve> handlers = new HashMap<>();

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

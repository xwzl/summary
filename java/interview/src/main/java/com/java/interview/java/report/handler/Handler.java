package com.java.interview.java.report.handler;

import com.java.interview.java.report.domain.TemplateConfig;

import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 15:08
 */
public interface Handler {

    String getRouteKey();

    boolean prepare();

    boolean transfer(Map<String, Object> target, Map<String, Object> source, TemplateConfig templateConfig);
}

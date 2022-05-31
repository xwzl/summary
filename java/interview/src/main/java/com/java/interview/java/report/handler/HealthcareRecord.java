package com.java.interview.java.report.handler;

import com.java.interview.java.report.domain.ConfigItem;
import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.domain.TemplateConfig;
import com.java.interview.java.report.enums.HandlerEnum;
import com.java.interview.java.report.handler.nice.Nice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 硬编码
 *
 * @author xuweizhi
 * @since 2022/05/30 16:52
 */
public class HealthcareRecord implements Handler {

    private HandlerEnum KEY = HandlerEnum.HEALTHCARE_RECORD;

    @Override
    public String getRouteKey() {
        return KEY.getKey();
    }

    @Override
    public boolean prepare() {
        Map<Long, Map<String, Object>> list = new HashMap<>();
        Map<String, Object> sssss = new HashMap<>();
        sssss.put("id", 1L);
        sssss.put("patientName", "测试名称");
        sssss.put("age", "12");
        sssss.put("birth", LocalDate.now());
        sssss.put("birthDay", LocalDateTime.now());
        list.put(2L, sssss);
        ContextHolder.setValue(getRouteKey(), list);
        return false;
    }

    /**
     * 解析
     *
     * @param target
     * @param source
     * @param templateConfig
     * @return
     */
    @Override
    public boolean transfer(Map<String, Object> target, Map<String, Object> source, TemplateConfig templateConfig) {
        // String key = KEY.getKey();
        // List<Map<String, Object>> dataSource = (List<Map<String, Object>>) ContextHolder.getDataSource(key);
        // Resolve resolve = ContextHolder.getHandler(key);
        // Map<String, Object> stringObjectMap = dataSource.get(0);
        // resolve.resolvePattern(target, stringObjectMap);

        for (ConfigItem configItem : templateConfig.getConfigItem()) {
            Nice nice = configItem.getNice();
            target.put(configItem.getTarget(), nice.baby(source.get(configItem.getSource())));
        }
        return false;
    }
}

package com.java.interview.java.report.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.interview.java.report.domain.ConfigItem;
import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.domain.TemplateConfig;
import com.java.interview.java.report.enums.HandlerEnum;
import com.java.interview.java.report.handler.nice.Nice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 18:47
 */
public class AreaLogHandler implements Handler {

    private HandlerEnum key = HandlerEnum.AREA_LOG;

    @Override
    public String getRouteKey() {
        return key.getKey();
    }

    @Override
    public boolean prepare() {
        ObjectMapper om = new ObjectMapper();
        AreaLog areaLog = new AreaLog(1L, 2L, "base64", "base编码");

        String s1;
        Map<String, Object> userMap = null;
        try {
            s1 = om.writeValueAsString(areaLog);
            userMap = om.readValue(s1, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<Long, Map<String, Object>> map = new HashMap<>();
        map.put(areaLog.getId(), userMap);
        ContextHolder.setValue(getRouteKey(), map);
        return true;
    }

    @Override
    public boolean transfer(Map<String, Object> target, Map<String, Object> source, TemplateConfig templateConfig) {
        for (ConfigItem configItem : templateConfig.getConfigItem()) {
            Nice nice = configItem.getNice();
            target.put(configItem.getTarget(), nice.baby(source.get(configItem.getSource())));
        }
        return true;
    }


}

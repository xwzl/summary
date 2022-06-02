package com.java.interview.java.report.handler;

import com.java.interview.java.report.domain.ConfigItem;
import com.java.interview.java.report.domain.Context;
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
    public void prepare() {
        Map<Long, Map<String, Object>> list = new HashMap<>();
        Map<String, Object> sssss = new HashMap<>();
        sssss.put("id", 1L);
        sssss.put("patientName", "测试名称");
        sssss.put("age", "12");
        sssss.put("birth", LocalDate.now());
        sssss.put("birthDay", LocalDateTime.now());
        sssss.put("address", "Are you ok ?");
        list.put(2L, sssss);
        ContextHolder.setValue(HealthcareRecord.class.getName(), list);
    }

    /**
     * 解析
     *
     * @param target
     * @return
     */
    @Override
    public void transfer(Map<String, Object> target) {
        // String key = KEY.getKey();
        // List<Map<String, Object>> dataSource = (List<Map<String, Object>>) ContextHolder.getDataSource(key);
        // Resolve resolve = ContextHolder.getHandler(key);
        // Map<String, Object> stringObjectMap = dataSource.get(0);
        // resolve.resolvePattern(target, stringObjectMap);
        Context context = ContextHolder.getContext();
        TemplateConfig templateConfig = context.getTemplateConfig();
        Map<String, Object> source = context.getSource(Map.class);
        Boolean switchDoc = context.getSwitchDoc();
        for (ConfigItem configItem : templateConfig.getConfigItem()) {
            Nice nice = configItem.getNice();
            target.put(switchDoc ? configItem.getDocTarget() : configItem.getTarget(), nice.baby(source.get(configItem.getSource())));
        }
    }
}

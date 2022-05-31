package com.java.interview.java.report.handler;

import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.enums.HandlerEnum;
import com.java.interview.java.report.resolve.Resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 硬编码
 *
 * @author xuweizhi
 * @since 2022/05/30 16:52
 */
public class HealthcareRecord implements Handler {

    private HandlerEnum KEY = HandlerEnum.HEALTHCARE_RECORD_ENUM;
    @Override
    public boolean prepare() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> sssss = new HashMap<>();
        sssss.put("healthId", "1");
        sssss.put("healthId1", "2");
        sssss.put("healthId2", "3");
        list.add(sssss);
        ContextHolder.setValue(KEY.getKey(), list);
        return false;
    }

    /**
     * 解析
     *
     * @param target
     * @return
     */
    @Override
    public boolean transfer(Map<String, Object> target) {
        String key = KEY.getKey();
        List<Map<String, Object>> dataSource = (List<Map<String, Object>>) ContextHolder.getDataSource(key);

        Resolve resolve = ContextHolder.getHandler(key);
        Map<String, Object> stringObjectMap = dataSource.get(0);
        resolve.resolvePattern(target, stringObjectMap);
        return false;
    }
}

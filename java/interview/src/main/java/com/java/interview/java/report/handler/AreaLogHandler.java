package com.java.interview.java.report.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.interview.java.report.config.JsonUtils;
import com.java.interview.java.report.domain.ConfigItem;
import com.java.interview.java.report.domain.Context;
import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.domain.TemplateConfig;
import com.java.interview.java.report.handler.nice.Nice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 18:47
 */
public class AreaLogHandler implements Handler {

    @Override
    public void prepare() {
        AreaLog areaLog = new AreaLog(1L, 2L, "base64", "base编码");
        Map<Long, AreaLog> map = new HashMap<>(2);
        map.put(areaLog.getId(), areaLog);
        ContextHolder.setValue(AreaLogHandler.class.getName(), map);
    }

    @Override
    public void transfer(Map<String, Object> target) {
        Context context = ContextHolder.getContext();
        TemplateConfig templateConfig = context.getTemplateConfig();
        AreaLog source = context.getSource(AreaLog.class);
        Map<String, Object> stringMapMap = JsonUtils.toJavaMap(source, new TypeReference<Map<String, Object>>() {
        });
        Boolean switchDoc = context.getSwitchDoc();
        for (ConfigItem configItem : templateConfig.getConfigItem()) {
            Nice nice = configItem.getNice();
            target.put(switchDoc ? configItem.getDocTarget() : configItem.getTarget(), nice.baby(stringMapMap.get(configItem.getSource())));
        }
        // for (ConfigItem configItem : templateConfig.getConfigItem()) {
        //     Nice nice = configItem.getNice();
        //     target.put(configItem.getTarget(), nice.baby(source.get(configItem.getSource())));
        // }
        //
        // ConfigItem configItem = new ConfigItem();
        // if(configItem.getSource().equals("patientName")){
        //     target.put(configItem.getDocTarget(),areaLog.getPatientName());
        // }
        // Context context = ContextHolder.getContext();
        // AreaLog source = context.getSource(AreaLog.class);
        // target.put("itemCode",source.getItemCode());
    }


}

package com.java.interview.java.report.config;

import com.google.common.collect.Maps;
import com.java.interview.java.report.ConfigManager;
import com.java.interview.java.report.domain.Context;
import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.domain.Template;
import com.java.interview.java.report.domain.TemplateConfig;
import com.java.interview.java.report.handler.Handler;
import com.java.interview.java.report.monitor.LogMonitor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.java.interview.java.report.enums.HandlerEnum.HEALTHCARE_RECORD;

/**
 * 基础配置
 *
 * @author xuweizhi
 * @since 2022/05/30 19:19
 */
@Component
public class ConfigContainer implements InitializingBean {

    @Resource
    private ConfigManager configManager;

    private final Map<String, Template> templates = Maps.newHashMap();


    @SuppressWarnings("all")
    public void initContextHolder(String dataSetCode) {


        Template template = templates.get(dataSetCode);

        if (Objects.isNull(template)) {
            template = configManager.queryTemplateByDataSetCode(dataSetCode);
            templates.put(dataSetCode, template);
        }

        Context context = new Context();
        // for (TemplateConfig templateConfig : template.getTemplateConfigs()) {
        //     String name = templateConfig.getHandler().getClass().getName();
        //     ContextHolder.setValue(name, templateConfig);
        // }

        ContextHolder.setContextHolder(context);

        ContextHolder.setValue(HEALTHCARE_RECORD.getKey(), dataSetCode);

        LogMonitor logMonitor = template.getLogMonitor();
        logMonitor.start();

        List<TemplateConfig> templateConfigs = template.getTemplateConfigs();
        for (TemplateConfig templateConfig : templateConfigs) {
            templateConfig.getHandler().prepare();
        }

        Handler handler = template.getTemplateConfigs().get(0).getHandler();
        Map<String, Map<String, Object>> dataSource = (Map<String, Map<String, Object>>) ContextHolder.getDataSource(handler.getRouteKey());

        List<Map<String, Object>> collect = dataSource.values().stream().map(source -> {
            Map<String, Object> target = new HashMap<>();
            for (TemplateConfig templateConfig : templateConfigs) {
                Handler handler1 = templateConfig.getHandler();
                Map<String, Map<String, Object>> dataSource1 = (Map<String, Map<String, Object>>) ContextHolder.getDataSource(handler1.getRouteKey());
                Object key = source.get(templateConfig.getMappingField());
                Map<String, Object> source1 = dataSource1.get(Long.parseLong(key.toString()));
                handler1.transfer(target, source1, templateConfig);
            }
            return target;
        }).collect(Collectors.toList());

        logMonitor.statics(collect);

        ContextHolder.remove();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initContextHolder("1");
    }
}

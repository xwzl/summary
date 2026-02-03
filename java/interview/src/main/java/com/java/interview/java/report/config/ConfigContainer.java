package com.java.interview.java.report.config;

import com.google.common.collect.Maps;
import com.java.interview.java.report.ConfigManager;
import com.java.interview.java.report.domain.*;
import com.java.interview.java.report.handler.Handler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import tools.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.java.interview.java.report.enums.HandlerEnum.COMMON_PARAM;

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

        Context context = initContext(dataSetCode, template);
        if (template.getSwitchLogMonitor()) {
            template.getLogMonitor().start();
        }

        List<TemplateConfig> templateConfigs = template.getTemplateConfigs();
        for (TemplateConfig templateConfig : templateConfigs) {
            templateConfig.getHandler().prepare();
        }

        Handler handler = template.getTemplateConfigs().get(0).getHandler();
        Map<String, Object> dataSource = (Map<String, Object>) ContextHolder.getDataSource(handler.getClass().getName());

        List<Map<String, Object>> collect = dataSource.values().stream().map(source -> {
            Map<String, Object> target = new HashMap<>();
            if (source instanceof Map) {
                context.setTempSource((Map<String, Object>) source);
            } else {
                context.setTempSource(JsonUtils.toJavaMap(source, new TypeReference<Map<String, Object>>() {
                }));
            }
            for (TemplateConfig templateConfig : templateConfigs) {
                Handler handler1 = templateConfig.getHandler();
                context.setTemplateConfig(templateConfig);
                context.setHandler(handler1);
                handler1.transfer(target);
            }
            return target;
        }).collect(Collectors.toList());

        if (template.getSwitchLogMonitor()) {
            template.getLogMonitor().statics(collect);
        }


        ContextHolder.remove();
    }

    private Context initContext(String dataSetCode, Template template) {
        Context context = new Context();
        context.setSwitchDoc(template.getSwitchDoc());
        context.setSwitchLogMonitor(template.getSwitchLogMonitor());
        ContextHolder.setContextHolder(context);
        CommonParam commonParam = new CommonParam();
        commonParam.setParam1(dataSetCode);
        ContextHolder.setValue(COMMON_PARAM.getKey(), commonParam);
        return context;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initContextHolder("1");
    }
}

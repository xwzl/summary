package com.java.interview.java.report.config;

import com.google.common.collect.Maps;
import com.java.interview.java.report.ConfigManager;
import com.java.interview.java.report.domain.Template;
import com.java.interview.java.report.domain.TemplateConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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


    public void initContextHolder(String dataSetCode) {
        Template template = templates.get(dataSetCode);
        if (Objects.isNull(template)) {

            template = configManager.queryTemplateByDataSetCode(dataSetCode);
            templates.put(dataSetCode, template);
        }

    }

    public void stopContextHolder(String dataSetCode) {

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initContextHolder("1");
    }
}

package com.java.interview.java.report;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.google.common.collect.Lists;
import com.java.interview.java.report.domain.ConfigItem;
import com.java.interview.java.report.domain.Template;
import com.java.interview.java.report.domain.TemplateConfig;
import com.java.interview.java.report.domain.entity.ConfigItemDO;
import com.java.interview.java.report.domain.entity.ConfigUnitDO;
import com.java.interview.java.report.domain.entity.TemplateConfigDO;
import com.java.interview.java.report.domain.entity.TemplateDO;
import com.java.interview.java.report.domain.mapper.ConfigItemMapper;
import com.java.interview.java.report.domain.mapper.ConfigUnitMapper;
import com.java.interview.java.report.domain.mapper.TemplateConfigMapper;
import com.java.interview.java.report.domain.mapper.TemplateMapper;
import com.java.interview.java.report.enums.TypeEnum;
import com.java.interview.java.report.handler.nice.EqualNice;
import com.java.interview.java.report.handler.Handler;
import com.java.interview.java.report.handler.nice.LocalDateTimeToString;
import com.java.interview.java.report.handler.nice.LocalDateToString;
import com.java.interview.java.report.handler.nice.Nice;
import com.java.interview.java.report.monitor.LogMonitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.java.interview.java.report.enums.SystemConfigEnum.SYSTEM_UNIT_CUSTOMIZE;
import static com.java.interview.java.report.enums.TypeEnum.LOCALDATE;

/**
 * @author xuweizhi
 * @since 2022/05/31 14:18
 */
@Component
public class ConfigManager {

    @Resource
    private TemplateMapper templateMapper;

    @Resource
    private TemplateConfigMapper templateConfigMapper;

    @Resource
    private ConfigUnitMapper configUnitMapper;

    @Resource
    private ConfigItemMapper configItemMapper;

    private TemplateConfig getTemplateConfig(TemplateConfigDO templateConfigDO) {
        List<ConfigItem> configItems = getConfigItems(templateConfigDO);
        return TemplateConfig.builder()
                .configItem(configItems)
                .handler((Handler) createObject(templateConfigDO.getHandlerName()))
                .templateConfigName(templateConfigDO.getTemplateConfigName())
                .mappingField(templateConfigDO.getMappingField())
                .build();
    }

    private List<ConfigItem> getConfigItems(TemplateConfigDO templateConfigDO) {
        List<ConfigUnitDO> configUnitList = ChainWrappers.lambdaQueryChain(configUnitMapper)
                .eq(ConfigUnitDO::getTemplateConfigId, templateConfigDO.getId())
                .list();

        List<ConfigItemDO> configItemList = ChainWrappers.lambdaQueryChain(configItemMapper)
                .in(ConfigItemDO::getId, configUnitList.stream().map(ConfigUnitDO::getConfigItemId).collect(Collectors.toList()))
                .eq(ConfigItemDO::getIsDeleted, 0)
                .list();

        return configItemList.stream().map(this::getConfigItem).collect(Collectors.toList());
    }

    private ConfigItem getConfigItem(ConfigItemDO item) {
        return ConfigItem.builder()
                .source(item.getSource())
                .sourceName(item.getSourceName())
                .sourceType(item.getSourceType())
                .target(item.getTarget()).docTarget(item.getDocTarget())
                .targetType(item.getTargetType())
                .targetName(item.getTargetName())
                .nice(matchNice(item))
                .build();
    }

    public static Nice matchNice(ConfigItemDO configItem) {

        List<String> sList = Arrays.asList(LOCALDATE.getCode(), TypeEnum.LOCALDATETIME.getCode());
        // 1. 类型相同
        if (configItem.getTargetType().equals(configItem.getSourceType())) {
            if (StringUtils.isNotEmpty(configItem.getRule()) && sList.contains(configItem.getSourceType())) {
                return Objects.equals(configItem.getSourceType(), LOCALDATE.getCode()) ? new LocalDateToString(configItem.getRule()) :
                        new LocalDateTimeToString(configItem.getRule());
            }
            return new EqualNice();
        }
        return new EqualNice();
    }

    public Template queryTemplateByDataSetCode(String dataSetCode) {
        TemplateDO templateDO = ChainWrappers.lambdaQueryChain(templateMapper).eq(TemplateDO::getDataSetCode, dataSetCode).one();
        // todo 空指针断言
        // 1. 创建日志监听器
        LogMonitor logMonitor = (LogMonitor) createObject(templateDO.getLogMonitor());
        // 2. 构建执行日志链
        List<TemplateConfig> templateConfigs = Lists.newArrayList();

        TemplateConfigDO templateConfig = ChainWrappers.lambdaQueryChain(templateConfigMapper)
                .eq(TemplateConfigDO::getTemplateId, templateDO.getId())
                .eq(TemplateConfigDO::getConfigSuitType, SYSTEM_UNIT_CUSTOMIZE.getCode())
                .one();

        templateConfigs.add(getTemplateConfig(templateConfig));

        buildCommon(templateDO, templateConfigs);

        return Template.builder().logMonitor(logMonitor)
                .dataSetCode(dataSetCode)
                .switchLogMonitor(templateDO.getSwitchLogMonitor())
                .templateName(templateDO.getTemplateName())
                .templateConfigs(templateConfigs)
                .build();
    }

    private void buildCommon(TemplateDO templateDO, List<TemplateConfig> templateConfigs) {
        List<TemplateConfigDO> list = ChainWrappers.lambdaQueryChain(templateConfigMapper)
                .eq(TemplateConfigDO::getTemplateId, templateDO.getId())
                .gt(TemplateConfigDO::getConfigSuitType, SYSTEM_UNIT_CUSTOMIZE.getCode())
                .list();
        Map<Integer, TemplateConfigDO> templateConfigMap = list.stream()
                .collect(Collectors.toMap(TemplateConfigDO::getConfigSuitType, Function.identity()));
        templateConfigMap.values().forEach(t -> templateConfigs.add(getTemplateConfig(t)));
    }


    private Object createObject(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return ClassUtils.newInstance(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

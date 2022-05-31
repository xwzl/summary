package com.java.interview.java.report.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.logging.Handler;

/**
 * 模板配置
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateConfig {

    private String templateConfigName;

    private String mappingField;

    private Handler handler;

    private List<ConfigItem> configItem;

}

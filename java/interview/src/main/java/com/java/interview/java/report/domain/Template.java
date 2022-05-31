package com.java.interview.java.report.domain;

import com.java.interview.java.report.monitor.LogMonitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class Template {

    private String templateName;

    private String dataSetCode;

    private LogMonitor logMonitor;

    private Boolean switchLogMonitor;

    private List<TemplateConfig> templateConfigs;

}

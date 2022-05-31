package com.java.interview.java.report.domain;

import com.java.interview.java.report.handler.nice.Nice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigItem {

    private String source;

    private String sourceName;

    private String sourceType;

    private String target;

    private String targetName;

    private String docTarget;

    private String targetType;

    private Nice nice;

}

package com.java.interview.java.report.domain;

import lombok.Data;

/**
 * 字段转换编码
 *
 * @author xuweizhi
 * @since 2022/05/30 16:55
 */
@Data
public class FieldMapping {

    private String source;

    private String target;

    private String documentTarget;

}

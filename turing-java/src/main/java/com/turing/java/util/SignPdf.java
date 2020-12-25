package com.turing.java.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/25 13:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignPdf {

    private String userId;
    private String configKey;
    private String signParams;
    //private File pdfFile;
    private String cloudCertPass;
}

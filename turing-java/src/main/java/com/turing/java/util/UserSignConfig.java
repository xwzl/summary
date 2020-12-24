package com.turing.java.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/24 18:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignConfig {
    private String userId;
    private String configKey;
    private String keypairType;
    private String certSn;
    private String signType;
    private String signTemplate;
    private String signParam;
    private String sealType;
    private String sealImg;
}

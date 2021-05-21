package com.turing.java.util.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/24 17:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertEnrollRes {
    private String signMethod;
    private String toSignEncoding;
    private String signP7;
    private String signBare;
    private String certSerialnumber;
    private String certBase64;
}

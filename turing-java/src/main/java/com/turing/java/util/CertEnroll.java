package com.turing.java.util;

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
public class CertEnroll {

    private String entityId;
    private String entityType;
    private String personalPhone;
    private String personalName;
    private String personalIdNumber;
    private String personalEmail;
    private String orgName;
    private String orgNumber;
    private String pin;
    private String toSign;
    private String orgDept;
    private String province;
    private String locality;

}

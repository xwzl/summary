package com.turing.java.util.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/25 12:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchUserSealRes {
    private String sealImg;
    private Integer sealType;
    private String id;
    private String appId;
}

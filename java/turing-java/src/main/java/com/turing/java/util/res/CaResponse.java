package com.turing.java.util.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/25 12:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaResponse<T> {
    private Integer result_code;
    private String result_msg;
    private T body;
    private Boolean success;
}

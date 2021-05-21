package com.turing.java.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/25 12:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchUserSeal {

    private String userId;
}

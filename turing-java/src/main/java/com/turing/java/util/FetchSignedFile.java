package com.turing.java.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/25 15:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchSignedFile {
    private String userId;
    private String fileId;
}

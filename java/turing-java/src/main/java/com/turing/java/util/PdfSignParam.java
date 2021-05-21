package com.turing.java.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/12/25 12:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfSignParam {

    private Integer llx;
    private Integer lly;
    private Integer urx;
    private Integer ury;
    private List<Integer> pageList;
    private String sealImg;

}

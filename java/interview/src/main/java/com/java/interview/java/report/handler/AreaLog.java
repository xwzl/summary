package com.java.interview.java.report.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaLog {

    private Long id;

    private Long healthcareRecordId;

    private String itemCode;

    private String itemName;
}

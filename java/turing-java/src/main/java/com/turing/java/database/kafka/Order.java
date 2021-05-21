package com.turing.java.database.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuweizhi
 * @since 2020/12/23 22:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer orderId;
    private Integer expire;
    private Integer num;
    private Double price;
}

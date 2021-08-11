package com.spring.cloud.dubbo.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuweizhi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * name
     */
    private String name;
}

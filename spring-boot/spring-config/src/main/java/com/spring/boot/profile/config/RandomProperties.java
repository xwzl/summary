package com.spring.boot.profile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring Random Filed test
 *
 * @author xuweizhi
 * @since 2020/11/14 17:04
 */
@Data
@ConfigurationProperties(prefix = "random")
public class RandomProperties {

    private String secret;

    private String number;

    private String bignumber;

    //private String uuid;

    private String numberLessThanTen;

    private String numberInRange;
}

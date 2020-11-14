package com.spring.boot.profile.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuweizhi
 * @since 2020/11/14 16:33
 */
@Data
@ToString
@ConfigurationProperties(prefix = "ip")
public class MigrateProperties {

    private String host;

    private Integer port;

    private String name;

    private String additional;
}

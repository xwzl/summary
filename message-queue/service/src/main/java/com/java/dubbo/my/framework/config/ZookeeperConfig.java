package com.java.dubbo.my.framework.config;

import com.java.dubbo.my.framework.protocol.auto.AutoConfig;
import com.java.dubbo.my.framework.protocol.auto.AutoValue;
import lombok.Data;

/**
 * config
 *
 * @author xuweizhi
 * @since 2021/05/26 16:29
 */
@Data
@AutoConfig
public class ZookeeperConfig {

    @AutoValue(value = "${enable}")
    private Boolean enable;
}

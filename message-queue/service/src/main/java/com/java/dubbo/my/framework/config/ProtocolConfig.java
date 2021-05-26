package com.java.dubbo.my.framework.config;


import com.java.dubbo.my.framework.protocol.auto.AutoConfig;
import com.java.dubbo.my.framework.protocol.auto.AutoValue;
import lombok.Data;

/**
 * 协议 配置
 *
 * @author xuweizhi
 * @since 2021/05/26 17:42
 */
@Data
@AutoConfig
public class ProtocolConfig {

    @AutoValue("${protocol}")
    private String protocol;
}


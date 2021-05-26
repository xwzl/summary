package com.java.dubbo.my.utils.proxy;

import com.java.dubbo.my.framework.Protocol;
import com.java.dubbo.my.framework.config.ApplicationConfig;
import com.java.dubbo.my.framework.config.ProtocolConfig;
import com.java.dubbo.my.framework.protocol.dubbo.DubboProtocol;
import com.java.dubbo.my.framework.protocol.http.HttpProtocol;

/**
 * 协议代理工厂
 *
 * @author xuweizhi
 * @since 2021/05/25 18:31
 */
public class ProtocolProxyFactory {

    public static final String BLANK_CHARACTER = "";

    private final static String PROTOCOL_NAME = "protocolName";

    public static final String HTTP_PROTOCOL = "http";

    public static final String DUBBO_PROTOCOL = "dubbo";


    public static Protocol getProtocol() {
        // jvm 参数 -DprotocalName=dubbo
        // 也可以用 SPI 机制来寻找服务实现类
        String name = System.getProperty(PROTOCOL_NAME);
        if (name == null || name.equals(BLANK_CHARACTER)) {
            name = HTTP_PROTOCOL;
        }
        if (ApplicationConfig.getApplicationFactory() != null) {
            ProtocolConfig protocolConfig = ApplicationConfig.getApplicationFactory().getBean(ProtocolConfig.class);
            if (protocolConfig.getProtocol() != null) {
                name = protocolConfig.getProtocol();
            }
        }
        switch (name) {
            case HTTP_PROTOCOL:
                return new HttpProtocol();
            case DUBBO_PROTOCOL:
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();

//        ExtensionLoader<Protocol> extensionLoader =
//                ExtensionLoader.getExtensionLoader(Protocol.class);

        // http
//        Protocol protocol = extensionLoader.getExtension(用户配置);

//        protocol.start(new URL("localhost", 8080));
    }
}

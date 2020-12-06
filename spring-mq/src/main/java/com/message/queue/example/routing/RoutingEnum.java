package com.message.queue.example.routing;

import lombok.Getter;

/**
 * routing key
 *
 * @author xuweizhi
 * @since 2020/12/06 17:04
 */
@Getter
public enum RoutingEnum {

    ONE(0, "default.key"),
    BAIDU_ONE(1, "baidu.sichuan"),
    BAIDU_TWO(2, "baidu.shanghai"),
    SINA_ONE(3, "sina.sichuan"),
    SINA_TWO(4, "sina.guangzhou"),
    SINA_THREE(5, "sina.shanghai");
    int code;
    String routing;

    RoutingEnum(int code, String routing) {
        this.code = code;
        this.routing = routing;
    }

    public static RoutingEnum getRoutingEnumByCode(int code) {
        for (RoutingEnum routingEnum : RoutingEnum.values()) {
            if (routingEnum.getCode() == code) {
                return routingEnum;
            }
        }
        throw new RuntimeException("传入的 routing key 错误");
    }
}

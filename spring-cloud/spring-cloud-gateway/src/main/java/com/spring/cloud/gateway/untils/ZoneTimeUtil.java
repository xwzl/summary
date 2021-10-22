package com.spring.cloud.gateway.untils;

import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * spring-cloud-gateway: predicates 时间工具
 *
 * @author xuweizhi
 * @since 2021/10/21 10:56
 */
@Slf4j
public class ZoneTimeUtil {

    /**
     * 获取 ZoneTimeStr
     *
     * @param offsetTime offsetTime < 0 表示 -
     * @return ZoneTimeStr
     */
    public static String getZoneTimeStr(Integer offsetTime) {
        // ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        zonedDateTime.plusHours(offsetTime);
        String timeZoneString = zonedDateTime.toString();
        log.info("TimeZone is {}", timeZoneString);
        return timeZoneString;
    }

    public static void main(String[] args) {
        String zoneTimeStr = ZoneTimeUtil.getZoneTimeStr(-1);
    }
}

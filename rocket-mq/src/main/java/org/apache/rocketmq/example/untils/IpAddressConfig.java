package org.apache.rocketmq.example.untils;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2020/12/17 18:15
 */
public class IpAddressConfig {

    public final static String COMPANY_IP = "192.168.232.10:9876;192.168.232.10:9877";
    public final static String HOME_IP = "192.168.232.10:9876;192.168.232.10:9877";

    public static String getRabbitMqAddress() {
        return COMPANY_IP;
    }
}

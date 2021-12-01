package org.apache.rocketmq.example.untils;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2020/12/17 18:15
 */
public class IpAddressConfig {

    public final static String COMPANY_IP = "10.200.201.145:9876";
    //public final static String HOME_IP = "192.168.208.128:9876";

    public static String getRabbitMqAddress() {
        return COMPANY_IP;
    }
}

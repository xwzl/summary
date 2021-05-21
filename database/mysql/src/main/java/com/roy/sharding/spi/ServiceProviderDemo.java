package com.roy.sharding.spi;

import java.util.ServiceLoader;

/**
 * @author xuweizhi
 * @since 2021/02/05 10:47
 */
public class ServiceProviderDemo {

    public static void main(String[] args) {
        ServiceLoader<Computer> serviceLoader = ServiceLoader.load(Computer.class);
        serviceLoader.forEach(Computer::description);
    }
}

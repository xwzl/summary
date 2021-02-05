package com.roy.sharding.spi;

/**
 * @author xuweizhi
 * @since 2021/02/05 10:45
 */
public class HostComputer implements Computer {
    @Override
    public void description() {
        System.out.println("桌面主机");
    }
}

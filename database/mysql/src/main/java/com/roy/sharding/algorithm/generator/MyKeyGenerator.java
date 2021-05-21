package com.roy.sharding.algorithm.generator;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuweizhi
 * @since 2021/02/05 15:52
 */
public class MyKeyGenerator implements ShardingKeyGenerator {

    AtomicLong atomicLong = new AtomicLong(1);

    @Override
    public Comparable<?> generateKey() {
        return atomicLong.getAndIncrement();
    }

    @Override
    public String getType() {
        return "MYKEY";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

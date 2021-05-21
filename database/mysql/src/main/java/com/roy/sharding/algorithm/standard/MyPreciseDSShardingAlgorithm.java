package com.roy.sharding.algorithm.standard;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;


/**
 * 标准精确分库策略
 */
public class MyPreciseDSShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    //select * from course where id = ? or id in (?,?)
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        String cid = shardingValue.getColumnName();
        Long cidValue = shardingValue.getValue();
        //实现 course_$->{id%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(cidValue);
        BigInteger resB = (shardingValueB.mod(new BigInteger("2")));
        // 与配置库名相匹配spring.shardingsphere.datasource:names: test1,test2
        String key = "test" + resB;
        if (availableTargetNames.contains(key)) {
            return key;
        }
        //couse_1, course_2
        throw new UnsupportedOperationException("route " + key + " is not supported ,please check your config");
    }
}

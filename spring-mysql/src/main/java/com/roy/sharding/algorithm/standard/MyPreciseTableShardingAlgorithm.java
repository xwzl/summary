package com.roy.sharding.algorithm.standard;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;


/**
 * 标准精确分表策略
 */
public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    /**
     * select * from course where id = ? or id in (?,?)
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // 逻辑表的名称
        String logicTableName = shardingValue.getLogicTableName();
        // 分片键对应 mysql 字段的名称
        String cid = shardingValue.getColumnName();
        // 主键值
        Long cidValue = shardingValue.getValue();
        //实现 course_$->{id%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(cidValue);
        BigInteger resB = (shardingValueB.mod(new BigInteger("2")));
        String key = logicTableName + "_" + resB;
        if (availableTargetNames.contains(key)) {
            return key;
        }
        //couse_1, course_2
        throw new UnsupportedOperationException("route " + key + " is not supported ,please check your config");
    }
}

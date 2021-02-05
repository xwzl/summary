package com.roy.sharding.algorithm.standard;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * 标准范围分表策略
 */
public class MyRangeTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        //select * from course where id between 1 and 100;
        Long upperVal = shardingValue.getValueRange().upperEndpoint();//100
        Long lowerVal = shardingValue.getValueRange().lowerEndpoint();//1

        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName + "_1", logicTableName + "_0");
    }
}

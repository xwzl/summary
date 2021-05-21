package com.roy.sharding.algorithm.complex;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyComplexDSShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
//    SELECT  id,name,user_id,status  FROM course
//    WHERE  id BETWEEN ? AND ? AND user_id = ?
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Range<Long> cidRange = shardingValue.getColumnNameAndRangeValuesMap().get("id");
        Collection<Long> userIdCol = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");

        Long upperVal = cidRange.upperEndpoint();
        Long lowerVal = cidRange.lowerEndpoint();

        List<String> res = new ArrayList<>();

        for(Long userId: userIdCol){
            //course_{userID%2+1}
            BigInteger userIdB = BigInteger.valueOf(userId);
            BigInteger target = (userIdB.mod(new BigInteger("2")));

            res.add("test"+target);
        }

        return res;
    }
}

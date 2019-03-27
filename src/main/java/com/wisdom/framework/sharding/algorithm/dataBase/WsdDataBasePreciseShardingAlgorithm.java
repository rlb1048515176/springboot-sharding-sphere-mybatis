package com.wisdom.framework.sharding.algorithm.dataBase;


import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;


/**
 *  精确分片算法(此分片策略与RangeShardingAlgorithm配合使用，必须使用)
 *  用于处理使用“单一键”作为分片键的“=” 与 “IN”进行分片的场景。需要配合StandardShardingStrategy使用
 */
public class WsdDataBasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     *  取时间年份作为表的后缀
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(preciseShardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }


}

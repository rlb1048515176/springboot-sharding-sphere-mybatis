package com.wisdom.framework.sharding.algorithm;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import java.util.Collection;



/**
 *  精确分片算法(此分片策略与RangeShardingAlgorithm配合使用，必须使用)
 *  用于处理使用“单一键”作为分片键的“=” 与 “IN”进行分片的场景。需要配合StandardShardingStrategy使用
 */
public class WsdPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String>{

    public WsdPreciseShardingAlgorithm() {
        System.out.println("WsdPreciseShardingAlgorithm");
   }

    /**
     *  取时间年份作为表的后缀
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        System.out.println("WsdPreciseShardingAlgorithm.........");
        String tableSuffix = String.valueOf(preciseShardingValue.getValue()).substring(0,4);
        return preciseShardingValue.getLogicTableName() + "_" + tableSuffix;
    }
}

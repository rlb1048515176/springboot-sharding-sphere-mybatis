package com.wisdom.framework.sharding.algorithm;

import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;


import java.util.Collection;

/**
 * 复合分片算法
 * 用于处理使用“多键”作为分片键进行分片的场景，包含多个分片键的逻辑较复杂，需要应用开发者自行处理其中的复杂度。需要配合ComplexShardingStrategy使用。
 */
public class WsdComplexKeysShardingAlgorithm  implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> collection1) {
        System.out.println("WsdComplexKeysShardingAlgorithm.........");
        return null;
    }
}

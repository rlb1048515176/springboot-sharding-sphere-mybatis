package com.wisdom.framework.sharding.algorithm;

import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.hint.HintShardingAlgorithm;

import java.util.Collection;

/**
 * Hint分片算法
 * 用于处理使用Hint行分片的场景。需要配合HintShardingStrategy使用。
 *
 * 介绍：
 * 对于分片字段非SQL决定，而由其他外置条件决定的场景，可使用SQL Hint灵活的注入分片字段。
 * 例：内部系统，按照员工登录主键分库，
 * 而数据库中并无此字段。SQL Hint支持通过Java API和SQL注释(待实现)两种方式使用。
 */
public class WsdHintShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ShardingValue shardingValue) {
        System.out.println("WsdHintShardingAlgorithm.........");
        return null;
    }
}

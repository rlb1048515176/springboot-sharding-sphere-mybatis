package com.wisdom.framework.sharding.config.sharding.key;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import io.shardingsphere.core.keygen.KeyGenerator;

/**
 * <p>
 * 自定义雪花算法，替换 DefaultKeyGenerator，避免DefaultKeyGenerator生成的id大几率是偶数
 * </p>
 *
 * @package: com.xkcoding.sharding.jdbc.config
 * @description: 自定义雪花算法，替换 DefaultKeyGenerator，避免DefaultKeyGenerator生成的id大几率是偶数
 */
public class CustomSnowflakeKeyGenerator implements KeyGenerator {
    private static final Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    public CustomSnowflakeKeyGenerator() {
        System.out.println("CustomSnowflakeKeyGenerator 初始化.............");
    }

    @Override
    public Number generateKey() {
        System.out.println(snowflake.nextId());
        return snowflake.nextId();
    }
}
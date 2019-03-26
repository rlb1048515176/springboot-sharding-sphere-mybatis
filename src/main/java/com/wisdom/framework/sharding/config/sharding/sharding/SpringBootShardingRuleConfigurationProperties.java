package com.wisdom.framework.sharding.config.sharding.sharding;

import io.shardingsphere.core.yaml.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "sharding.jdbc.config.sharding"
)
public class SpringBootShardingRuleConfigurationProperties extends YamlShardingRuleConfiguration {
    public SpringBootShardingRuleConfigurationProperties() {
    }
}

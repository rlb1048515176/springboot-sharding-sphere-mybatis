package com.wisdom.framework.sharding.config.sharding.masterslave;

import io.shardingsphere.core.yaml.masterslave.YamlMasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "sharding.jdbc.config.masterslave"
)
public class SpringBootMasterSlaveRuleConfigurationProperties extends YamlMasterSlaveRuleConfiguration {
    public SpringBootMasterSlaveRuleConfigurationProperties() {
    }
}

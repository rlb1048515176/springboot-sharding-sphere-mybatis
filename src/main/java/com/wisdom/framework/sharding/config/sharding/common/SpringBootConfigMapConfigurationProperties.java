package com.wisdom.framework.sharding.config.sharding.common;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "sharding.jdbc.config"
)
public class SpringBootConfigMapConfigurationProperties {
    private Map<String, Object> configMap = new LinkedHashMap();

    public SpringBootConfigMapConfigurationProperties() {
    }

    public Map<String, Object> getConfigMap() {
        return this.configMap;
    }

    public void setConfigMap(Map<String, Object> configMap) {
        this.configMap = configMap;
    }
}

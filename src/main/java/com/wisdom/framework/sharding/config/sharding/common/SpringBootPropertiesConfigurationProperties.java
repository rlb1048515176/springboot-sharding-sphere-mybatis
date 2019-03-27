package com.wisdom.framework.sharding.config.sharding.common;

import java.util.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "sharding.jdbc.config"
)
public class SpringBootPropertiesConfigurationProperties {
    private Properties props = new Properties();

    public SpringBootPropertiesConfigurationProperties() {
    }

    public Properties getProps() {
        return this.props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}

package com.wisdom.framework.sharding.config.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 *
 * 数据库集合
 */
/*@Configuration
@ConfigurationProperties("wsdDataSourceList")*/
@Deprecated
public class WsdDataSourceList {
    /**数据库驱动**/
    private String type;
    /**数据库集合**/
    private List<WsdDataSource> dataSources;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WsdDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<WsdDataSource> dataSources) {
        this.dataSources = dataSources;
    }

}

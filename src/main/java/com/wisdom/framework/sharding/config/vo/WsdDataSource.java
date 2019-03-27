package com.wisdom.framework.sharding.config.vo;

import java.util.List;

/**
 *
 * 数据库对象
 */
@Deprecated
public class WsdDataSource {
    /**数据库驱动**/
    private String driverClassName;
    /**数据库连接地址**/
    private String url;
    /**用户名**/
    private String username;
    /**密码**/
    private String password;
    /**数据库名称**/
    private String dataSourceName;
    /**从库**/
    private List<WsdDataSource> slaveWsdDataSource;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public List<WsdDataSource> getSlaveWsdDataSource() {
        return slaveWsdDataSource;
    }

    public void setSlaveWsdDataSource(List<WsdDataSource> slaveWsdDataSource) {
        this.slaveWsdDataSource = slaveWsdDataSource;
    }
}

package com.wisdom.framework.sharding.config.sharding;
import com.github.pagehelper.PageInterceptor;
import com.google.common.base.Preconditions;
import com.wisdom.framework.sharding.config.sharding.common.SpringBootConfigMapConfigurationProperties;
import com.wisdom.framework.sharding.config.sharding.common.SpringBootPropertiesConfigurationProperties;
import com.wisdom.framework.sharding.config.sharding.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import com.wisdom.framework.sharding.config.sharding.sharding.SpringBootShardingRuleConfigurationProperties;
import com.wisdom.framework.sharding.config.sharding.util.PropertyUtil;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.core.exception.ShardingException;

import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

import io.shardingsphere.shardingjdbc.util.DataSourceUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@EnableConfigurationProperties({SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootConfigMapConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class})
public class SpringBootShardingConfiguration implements EnvironmentAware {
    private final SpringBootShardingRuleConfigurationProperties shardingProperties;
    private final SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties;
    private final SpringBootConfigMapConfigurationProperties configMapProperties;
    private final SpringBootPropertiesConfigurationProperties propMapProperties;
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap();

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        return null == this.masterSlaveProperties.getMasterDataSourceName() ? ShardingDataSourceFactory.createDataSource(this.dataSourceMap, this.shardingProperties.getShardingRuleConfiguration(), this.configMapProperties.getConfigMap(), this.propMapProperties.getProps()) : MasterSlaveDataSourceFactory.createDataSource(this.dataSourceMap, this.masterSlaveProperties.getMasterSlaveRuleConfiguration(), this.configMapProperties.getConfigMap(), this.propMapProperties.getProps());
    }

    public final void setEnvironment(Environment environment) {
        this.setDataSourceMap(environment);
    }

    private void setDataSourceMap(Environment environment) {
        String prefix = "sharding.jdbc.datasource.";
        String dataSources = environment.getProperty(prefix + "names");
        String[] var4 = dataSources.split(",");
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String each = var4[var6];

            try {
                Map<String, Object> dataSourceProps = (Map)PropertyUtil.handle(environment, prefix + each.trim(), Map.class);
                Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource properties!");
                DataSource dataSource = DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
                this.dataSourceMap.put(each, dataSource);
            } catch (ReflectiveOperationException var10) {
                throw new ShardingException("Can't find datasource type!", var10);
            }
        }

    }

    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "configMapProperties", "propMapProperties"})
    public SpringBootShardingConfiguration(SpringBootShardingRuleConfigurationProperties shardingProperties, SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties, SpringBootConfigMapConfigurationProperties configMapProperties, SpringBootPropertiesConfigurationProperties propMapProperties) {
        this.shardingProperties = shardingProperties;
        this.masterSlaveProperties = masterSlaveProperties;
        this.configMapProperties = configMapProperties;
        this.propMapProperties = propMapProperties;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //设置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties=new Properties();
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //创建SqlSessionTemplate
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

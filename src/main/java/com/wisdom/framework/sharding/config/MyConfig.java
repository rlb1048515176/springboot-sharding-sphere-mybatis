package com.wisdom.framework.sharding.config;

//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.github.pagehelper.PageInterceptor;
//import com.wisdom.framework.sharding.algorithm.WsdPreciseShardingAlgorithm;
//import com.wisdom.framework.sharding.algorithm.WsdRangeShardingAlgorithm;
//import com.wisdom.framework.sharding.algorithm.dataBase.WsdDataBasePreciseShardingAlgorithm;
//import com.wisdom.framework.sharding.algorithm.dataBase.WsdDataBaseRangeShardingAlgorithm;
//import com.wisdom.framework.sharding.config.vo.WsdDataSource;
//import com.wisdom.framework.sharding.config.vo.WsdDataSourceList;
//import io.shardingsphere.api.algorithm.masterslave.RandomMasterSlaveLoadBalanceAlgorithm;
//import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
//import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
//import io.shardingsphere.api.config.rule.TableRuleConfiguration;
//import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
//import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.*;
//import java.util.stream.Collectors;

/**
 * 分库分表动态配置
 */
@Deprecated
//@Configuration
//@EnableTransactionManagement
public class MyConfig {

    /*@Autowired
    private WsdDataSourceList wsdDataSourceList;

    TableRuleConfiguration getActionLogIdTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("user_action_log_id");
        result.setActualDataNodes("ds_0.user_action_log_id_${2016..2017}");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",new WsdDataBasePreciseShardingAlgorithm(),new WsdDataBaseRangeShardingAlgorithm()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("date",new WsdPreciseShardingAlgorithm(),new WsdRangeShardingAlgorithm()));
        //result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    TableRuleConfiguration getActionLogTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("user_action_log");
        result.setActualDataNodes("ds_0.user_action_log_${2016..2019}");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",new WsdDataBasePreciseShardingAlgorithm(),new WsdDataBaseRangeShardingAlgorithm()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("date",new WsdPreciseShardingAlgorithm(),new WsdRangeShardingAlgorithm()));
        return result;
    }

//    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
//        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration();
//        result.setColumn("order_id");
//        return result;
//    }

    //获取数据源集合
    public Map<String, DataSource> createDataSourceMap() throws Exception {
        Map<String, DataSource> map = new HashMap();
        for (WsdDataSource wsdDataSource:wsdDataSourceList.getDataSources()){
            Properties properties = new Properties();
            properties.put("driverClassName",wsdDataSource.getDriverClassName());
            properties.put("url", wsdDataSource.getUrl());
            properties.put("username", wsdDataSource.getUsername());
            properties.put("password", wsdDataSource.getPassword());
            DataSource masterDataSource = DruidDataSourceFactory.createDataSource(properties);
            map.put(wsdDataSource.getDataSourceName(),masterDataSource);
            if(wsdDataSource.getSlaveWsdDataSource()!=null && wsdDataSource.getSlaveWsdDataSource().size()>0 ){
                for (WsdDataSource slaveSource : wsdDataSource.getSlaveWsdDataSource()) {
                    Properties slaveProperties = new Properties();
                    slaveProperties.put("driverClassName",slaveSource.getDriverClassName());
                    slaveProperties.put("url", slaveSource.getUrl());
                    slaveProperties.put("username", slaveSource.getUsername());
                    slaveProperties.put("password", slaveSource.getPassword());
                    DataSource slaveDataSource = DruidDataSourceFactory.createDataSource(slaveProperties);
                    map.put(slaveSource.getDataSourceName(),slaveDataSource);
                }
            }
        }
        return map;
    }

    //获取主从关系集合
    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurationList = new ArrayList();
        for (WsdDataSource wsdDataSource:wsdDataSourceList.getDataSources()){
            //获取从库名称集合
            if(wsdDataSource.getSlaveWsdDataSource() != null && wsdDataSource.getSlaveWsdDataSource().size() >0 ){
                List<String> slaveList = (wsdDataSource.getSlaveWsdDataSource()).stream().map(WsdDataSource::getDataSourceName).collect(Collectors.toList());
                //设置主从关联
                MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration(wsdDataSource.getDataSourceName(), wsdDataSource.getDataSourceName(),slaveList,new RandomMasterSlaveLoadBalanceAlgorithm());
                masterSlaveRuleConfigurationList.add(masterSlaveRuleConfig);
            }
        }
         return masterSlaveRuleConfigurationList;
    }

    //创建分片数据源
    @Bean
    DataSource getDataSource() throws Exception {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getActionLogIdTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getActionLogTableRuleConfiguration());
//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//        shardingRuleConfig.getBroadcastTables().add("t_config");
        //设置默认的分库策略
        // shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new ModuloShardingDatabaseAlgorithm()));
        //设置默认的分表策略
        //shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new ModuloShardingTableAlgorithm()));
        //判定是否开启主从读写分离
        if(getMasterSlaveRuleConfigurations().size() > 0){
            shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());
        }
        Properties props = new Properties();
        props.put("sql.show",true);
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new HashMap<String, Object>(), props);
    }

    //创建SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.wisdom.framework.sharding.entity");
        //设置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties=new Properties();
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //创建SqlSessionTemplate
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //创建SqlSessionTemplate
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws Exception {
        return new DataSourceTransactionManager(getDataSource());
    }
*/

}

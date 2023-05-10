package com.example.mybatisdemo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mybatisdemo.datasource.RoutingDataSource;
import com.example.mybatisdemo.enums.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DataSourceConfigurer {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresqlDatasource() {
        return new DruidDataSource();
    }

    @Bean
    public RoutingDataSource routingDataSource(DataSource mysqlDatasource, DataSource postgresqlDatasource) {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.MYSQL, mysqlDatasource);
        dataSources.put(DataSourceType.POSTGRESQL, postgresqlDatasource);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(mysqlDatasource);
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }
}

package com.example.mybatisdemo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 这里需要配置扫描包路径，以及sqlSessionTemplateRef
@MapperScan(basePackages = "com.example.mybatisdemo.mapper.mysql", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlMybatisConfigurer {
    /**
     * 注入Mysql数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return new DruidDataSource();
    }

    /**
     * 注入mysqlSqlSessionFactory
     */
    @Bean
    public SqlSessionFactory mysqlSqlSessionFactory(DataSource mysqlDatasource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysqlDatasource);
        // 设置对应的mapper文件
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:" +
                "/mappers/MysqlMapper.xml"));
        return factoryBean.getObject();
    }

    /**
     * 注入mysqlSqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate mysqlSqlSessionTemplate(SqlSessionFactory mysqlSqlSessionFactory) {
        return new SqlSessionTemplate(mysqlSqlSessionFactory);
    }

    /**
     * 注入mysqlTransactionalManager
     */
    @Bean
    public DataSourceTransactionManager mysqlTransactionalManager(DataSource mysqlDatasource) {
        return new DataSourceTransactionManager(mysqlDatasource);
    }
}

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
@MapperScan(basePackages = "com.example.mybatisdemo.mapper.postgresql", sqlSessionTemplateRef = "postgresqlSqlSessionTemplate")
public class PostgresqlMybatisConfigurer {
    /**
     * 注入Postgresql数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresqlDatasource() {
        return new DruidDataSource();
    }

    /**
     * 注入postgresqlSqlSessionFactory
     */
    @Bean
    public SqlSessionFactory postgresqlSqlSessionFactory(DataSource postgresqlDatasource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(postgresqlDatasource);
        // 设置对应的mapper文件
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:" +
                "/mappers/PostgresqlMapper.xml"));
        return factoryBean.getObject();
    }

    /**
     * 注入postgresqlSqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate postgresqlSqlSessionTemplate(SqlSessionFactory postgresqlSqlSessionFactory) {
        return new SqlSessionTemplate(postgresqlSqlSessionFactory);
    }

    /**
     * 注入postgresqlTransactionalManager
     */
    @Bean
    public DataSourceTransactionManager postgresqlTransactionalManager(DataSource postgresqlDatasource) {
        return new DataSourceTransactionManager(postgresqlDatasource);
    }
}

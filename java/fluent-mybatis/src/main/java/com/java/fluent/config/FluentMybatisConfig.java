package com.java.fluent.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author xuweizhi
 */
// @ComponentScan(basePackages = "com.java.fluent")
// @MapperScan("com.java.fluent.mapper")
// @Configuration
public class FluentMybatisConfig {
    /**
     * 设置dataSource属性
     */
    // @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        //为了方便，直接硬编码了，我们可以通过@Value引入外部配置，
        //如果使用Spring boot就更简单了，直接使用@ConfigurationProperties引入外部配置
        //简单的配置数据库连接信息，其他连接池信息采用默认配置
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }

    /**
     * 定义mybatis的SqlSessionFactoryBean
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource druidDataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        return bean;
    }
}

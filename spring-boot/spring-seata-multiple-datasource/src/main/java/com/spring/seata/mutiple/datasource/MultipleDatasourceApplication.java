package com.spring.seata.mutiple.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * @author xuweizhi
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultipleDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class, args);
    }

}

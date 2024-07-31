package com.roy.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.roy.sharding.mapper")
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class,args);
    }
}

package com.spring.zookeeper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.spring.zookeeper.distributedlock.mapper")
@SpringBootApplication
public class DistributedlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedlockApplication.class, args);
	}

}

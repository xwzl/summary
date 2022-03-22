package com.spring.rocket.consumer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
	private Long id;
	private String accountName;
	private String accountNo;
	private Double accountBalance;
}

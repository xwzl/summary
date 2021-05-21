package com.roy.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("user")
@ToString
public class User {

    @TableId
    private Long id;
    private String username;
    private String status;
    private int age;
}

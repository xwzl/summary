package com.roy.sharding.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Course {

    // @TableId
    private Long id;
    private String name;
    private Long userId;
    private String status;


}

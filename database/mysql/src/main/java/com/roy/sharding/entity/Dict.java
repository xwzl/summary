package com.roy.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dict")
public class Dict {
    @TableId
    private Long id;
    private String status;
    private String value;

}

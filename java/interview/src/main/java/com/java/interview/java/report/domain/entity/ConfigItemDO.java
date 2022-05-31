package com.java.interview.java.report.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 配置项
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Data
@ToString
@TableName("config_item")
public class ConfigItemDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String source;

    private String sourceName;

    private String sourceType;

    private String target;

    private String targetName;

    private String docTarget;


    private String targetType;

    private String rule;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;

}

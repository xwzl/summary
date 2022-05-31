package com.java.interview.java.report.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模板名称
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Data
@ToString
@TableName("template")
public class TemplateDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 数据集编码
     */
    private String dataSetCode;

    /**
     * 监听器名称
     */
    private String logMonitor;

    /**
     * 监听器开关
     */
    private Boolean switchLogMonitor;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;


}

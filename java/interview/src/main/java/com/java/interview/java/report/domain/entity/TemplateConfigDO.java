package com.java.interview.java.report.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模板配置
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Getter
@Setter
@TableName("template_config")
@ToString
public class TemplateConfigDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 组合名称
     */
    private String templateConfigName;

    /**
     * 模块类型
     */
    private Integer configSuitType;

    /**
     * 映射字段
     */
    private String mappingField;

    /**
     * 处理名称
     */
    private String handlerName;

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

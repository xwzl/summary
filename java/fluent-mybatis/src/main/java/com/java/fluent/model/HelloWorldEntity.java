package com.java.fluent.model;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

import java.util.Date;

/**
 * FluentMybatis 可使用数据库任何操作
 *
 * @author xuweizhi
 */
@Data
@FluentMybatis(table="hello_world")
public class HelloWorldEntity implements IEntity {

    @TableId
    private Long id;

    private String sayHello;

    private String yourName;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;
}

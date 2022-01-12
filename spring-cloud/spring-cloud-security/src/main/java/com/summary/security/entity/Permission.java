package com.summary.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限表
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Data
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父权限
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限英文名称
     */
    private String englishName;

    /**
     * 授权路径
     */
    private String url;

    /**
     * 备注
     */
    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

}

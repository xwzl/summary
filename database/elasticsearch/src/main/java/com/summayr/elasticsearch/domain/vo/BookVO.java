package com.summayr.elasticsearch.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 书索引
 *
 * @author xuweizhi
 * @since 2021/07/19 19:17
 */
@Data
@ApiModel("书 VO 对象")
public class BookVO implements Serializable {

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private String id;

    /**
     * 书名
     */
    @ApiModelProperty("书名")
    private String bookName;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Float price;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
}

package com.summar.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 书索引
 *
 * @author xuweizhi
 * @since 2021/07/19 19:17
 */
@Data
@Document(indexName = "book")
public class BookDO implements Serializable {

    /**
     * 主键索引
     */
    @Id
    private String id;

    private String bookName;

    private String author;

    private Float price;

    private Date createTime;
}

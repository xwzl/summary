package com.java.dubbo.my.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 书
 *
 * @author xuweizhi
 * @since 2021/05/25 13:59
 */
@Data
public class BookVO {

    /**
     * 书名
     */
    private String bookName;
    /**
     * 作者
     */
    private String author;
    /**
     * 书的描述信息
     */
    private String bookDesc;
    /**
     * 书的价格
     */
    private BigDecimal bigDecimal;


    public BookVO() {
    }

    public BookVO(String bookName, String author, String bookDesc, BigDecimal bigDecimal) {
        this.bookName = bookName;
        this.author = author;
        this.bookDesc = bookDesc;
        this.bigDecimal = bigDecimal;
    }

    public static BookVO bookFactory(String bookName, String author, String bookDesc, BigDecimal bigDecimal) {
        return new BookVO(bookName, author, bookDesc, bigDecimal);
    }
}

package com.turing.java.jvm.java8.date;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * value 赋值测试
 *
 * @author xuweizhi
 * @since 2021/04/13 14:55
 */
@Data
@ToString
public class AssignValue {

    private Book book;

    private String spaceChar;

    @Data
    @ToString
    private static class Book {
        private String bookName;
    }

    public static void main(String[] args) {
        AssignValue assignValue = new AssignValue();
        assignValue.setBook(new Book());
        assignValue.getBook().setBookName("飞机");
        assignValue.setSpaceChar(String.join(",", new ArrayList<>()));
        System.out.println(assignValue);
    }
}

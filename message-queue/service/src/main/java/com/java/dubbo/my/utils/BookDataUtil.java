package com.java.dubbo.my.utils;

import com.java.dubbo.my.model.BookVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Book
 *
 * @author xuweizhi
 * @since 2021/05/25 14:49
 */
public class BookDataUtil {

    public static Map<String, BookVO> books = new HashMap<>();

    static {
        books.put("1", BookVO.bookFactory("Java 入门到放弃", "Tom", "Java 职业技能介绍", new BigDecimal(25)));
        books.put("2", BookVO.bookFactory("Python 入门到放弃", "Jack", "Python 职业技能介绍", new BigDecimal(45)));
        books.put("3", BookVO.bookFactory("Go 入门到放弃", "Mary", "Go 职业技能介绍", new BigDecimal(78)));
        books.put("4", BookVO.bookFactory("C++ 入门到放弃", "SuSan", "C++ 职业技能介绍", new BigDecimal(101)));
    }

}

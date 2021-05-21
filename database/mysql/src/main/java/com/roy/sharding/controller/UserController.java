package com.roy.sharding.controller;

import com.roy.sharding.entity.Book;
import com.roy.sharding.entity.Course;
import com.roy.sharding.mapper.BookMapper;
import com.roy.sharding.mapper.CourseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.SecureRandom;

@RestController
public class UserController {



    @Resource
    private BookMapper bookMapper;

    @Resource
    private CourseMapper courseMapper;

    SecureRandom secureRandom = new SecureRandom();


    @GetMapping("/insertBook")
    public Integer insertBook(Book book) {
        return bookMapper.insert(book);
    }

    @GetMapping("/addCourse")
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            c.setName("shardingsphere");
            c.setUserId(getUserId());
            c.setStatus("1");
            courseMapper.insert(c);
        }
        // courseMapper.selectList(null).forEach(System.out::println);
    }

    private long getUserId() {
        long l = 0L;
        while ((l = secureRandom.nextLong()) > 0L) {
            return l;
        }
        return 0;
    }
}


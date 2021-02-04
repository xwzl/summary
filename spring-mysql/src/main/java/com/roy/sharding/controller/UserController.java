package com.roy.sharding.controller;

import com.roy.sharding.demo.User;
import com.roy.sharding.entity.Course;
import com.roy.sharding.mapper.CourseMapper;
import com.roy.sharding.mapper.UserDemoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserDemoMapper userDemoMapper;

    @Resource
    private CourseMapper courseMapper;

    SecureRandom secureRandom = new SecureRandom();

    @GetMapping("/select")
    public List<User> select() {
        return userDemoMapper.selectList(null);
    }

    @GetMapping("/insert")
    public Integer insert(User user) {
        return userDemoMapper.insert(user);
    }

    @GetMapping("/addCourse")
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            c.setCid(Long.valueOf(i));
            c.setCname("shardingsphere");
            c.setUserId(secureRandom.nextLong());
            c.setCstatus("1");
            courseMapper.insert(c);
        }
        courseMapper.selectList(null).forEach(System.out::println);
    }
}


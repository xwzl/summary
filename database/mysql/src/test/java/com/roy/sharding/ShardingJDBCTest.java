package com.roy.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roy.sharding.entity.Course;
import com.roy.sharding.entity.Dict;
import com.roy.sharding.entity.User;
import com.roy.sharding.mapper.CourseMapper;
import com.roy.sharding.mapper.DictMapper;
import com.roy.sharding.mapper.UserMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {
    @Resource
    CourseMapper courseMapper;
    @Resource
    DictMapper dictMapper;
    @Resource
    UserMapper userMapper;

    SecureRandom secureRandom = new SecureRandom();

    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            // c.setId(Long.valueOf(i));
            c.setName("shardingsphere");
            long userId = secureRandom.nextLong();
            c.setUserId(userId < 0 ? -userId : userId);
            c.setStatus("1");
            courseMapper.insert(c);
        }
        // courseMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void search() {
        courseMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void queryCourse() {
        //select * from course
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.eq("id", 553684818806706177L);
//        wrapper.in()
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    @Test
    public void queryOrderRange() {
        //select * from course
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("id", 553684818806706177L, 553684819184193537L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    @Test
    public void queryCourseComplex() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("id", 553684818806706177L, 553684819184193537L);
        wrapper.eq("user_id", 1009L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    @Test
    public void queryCourseByHint() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("course", 2);
        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(System.out::println);
        hintManager.close();
    }

    @Test
    public void addDict() {
        Dict d1 = new Dict();
        d1.setStatus("1");
        d1.setValue("正常");
        dictMapper.insert(d1);

        Dict d2 = new Dict();
        d2.setStatus("0");
        d2.setValue("不正常");
        dictMapper.insert(d2);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user No " + i);
            user.setStatus("" + (i % 2));
            user.setAge(i * 10);
            userMapper.insert(user);
        }
    }

    @Test
    public void queryUserStatus() {
        List<User> users = userMapper.queryUserStatus();
        users.forEach(System.out::println);
    }

    @Test
    public void addDictByMS() {
        Dict d1 = new Dict();
        d1.setStatus("1");
        d1.setValue("正常");
        dictMapper.insert(d1);

        Dict d2 = new Dict();
        d2.setStatus("0");
        d2.setValue("不正常");
        dictMapper.insert(d2);
    }

    @Test
    public void queryDictByMS() {
        List<Dict> dicts = dictMapper.selectList(null);
        dicts.forEach(System.out::println);
    }

}

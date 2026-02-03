package com.java.fluent;

import com.java.fluent.config.FluentMybatisConfig;
import com.java.fluent.mapper.HelloWorldMapper;
import com.java.fluent.model.HelloWorldEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jakarta.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FluentMybatisConfig.class)
public class HelloWorldTest {
    /**
     * fluent mybatis编译时生成的Mapper类
     */
    @Resource
    private HelloWorldMapper helloWorldMapper;

    @Test
    public void testHelloWorld() {
        /**
         * 为了演示方便，先删除数据
         */
        helloWorldMapper.delete(helloWorldMapper.query()
            .where.id().eq(1L).end());
        /**
         * 插入数据
         */
        HelloWorldEntity entity = new HelloWorldEntity();
        entity.setId(1L);
        entity.setSayHello("hello world");
        entity.setYourName("fluent mybatis");
        entity.setIsDeleted(false);
        helloWorldMapper.insert(entity);
        /**
         * 查询 id = 1 的数据
         */
        HelloWorldEntity result1 = helloWorldMapper.findOne(helloWorldMapper.query()
            .where.id().eq(1L).end());
        /**
         * 控制台直接打印出查询结果
         */
        System.out.println("1. HelloWorldEntity:" + result1.toString());
        /**
         * 更新id = 1的记录
         */
        helloWorldMapper.updateBy(helloWorldMapper.updater()
            .update.sayHello().is("say hello, say hello!")
            .set.yourName().is("fluent mybatis is powerful!").end()
            .where.id().eq(1L).end()
        );
        /**
         * 查询 id = 1 的数据
         */
        HelloWorldEntity result2 = helloWorldMapper.findOne(helloWorldMapper.query()
            .where.sayHello().like("hello")
            .and.isDeleted().eq(false).end()
            .limit(1)
        );
        /**
         * 控制台直接打印出查询结果
         */
        System.out.println("2. HelloWorldEntity:" + result2.toString());
    }
}

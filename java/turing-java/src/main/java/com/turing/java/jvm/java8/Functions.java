package com.turing.java.jvm.java8;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Function测试
 *
 * @author xuweizhi
 * @since 2020/08/07 16:50
 */
public class Functions {

    public static <T, R> void aop(Function<T, R> function, T t) {
        function.apply(t);
    }



    @Test
    public void test() {
        List<User> list = new ArrayList<>();
        list.add(new User("李1", 12));
        User user = new User("李", 12);
        Function<User, String> function = new Function<User, String>() {
            @Override
            public String apply(User user) {
                return null;
            }
        };


    }

    protected static class User {
        String name;
        Integer age;
        String description;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}

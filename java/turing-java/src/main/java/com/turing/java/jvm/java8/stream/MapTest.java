package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author xuweizhi
 */
public class MapTest {
    List<Student> students = new ArrayList<>();

    public void before() {
        students.add(new Student(1, 55, "小花"));
        students.add(new Student(2, 100, "小华"));
        students.add(new Student(3, 85, "晓华"));
        students.add(new Student(4, 70, "肖华"));
    }

    @Test
    public void test() {
        students.stream()
            // 获取每一个学生对象的学生id集合
            .map(Student::getId)
            // 这是一个终端消费操作，后面会讲
            .forEach(System.out::println);

        List<Integer> collect = students.stream()
            // 获取每一个学生对象的学生id集合
            .map(Student::getId)
            // 这是一个终端收集操作，后面会讲
            .collect(toList());
        System.out.println(collect);

        /*将小写字母转换为大写*/
        List<String> collected = Stream.of("a", "b", "C").map(String::toUpperCase).collect(toList());
        System.out.println(collected);
    }

    static class Student {
        private int id;
        private int score;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Student(int id, int score, String name) {
            this.id = id;
            this.score = score;
            this.name = name;
        }

        public Student(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" + "age=" + id + ", score=" + score + ", name='" + name + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Student))
                return false;

            Student student = (Student)o;

            if (getId() != student.getId())
                return false;
            if (getScore() != student.getScore())
                return false;
            return getName() != null ? getName().equals(student.getName()) : student.getName() == null;
        }

        @Override
        public int hashCode() {
            int result = getId();
            result = 31 * result + getScore();
            result = 31 * result + (getName() != null ? getName().hashCode() : 0);
            return result;
        }
    }
}

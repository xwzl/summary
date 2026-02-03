package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 */
public class FilterTest {


    List<Student> students = new ArrayList<>();

    public void test() {
        students.add(new Student(10, 55, "小花"));
        students.add(new Student(13, 100, "小华"));
        students.add(new Student(9, 85, "晓华"));
        students.add(new Student(8, 70, "肖华"));
        students.add(new Student(8, 70, "肖华"));
    }

    @Test
    public void filter() {
        System.out.println("filter筛选成绩大于等于70的学生");
        //filter筛选成绩大于等于70的学生
        students.stream().filter(student -> student.getScore() >= 70).forEach(System.out::println);

        System.out.println("filter+distinct筛选成绩大于等于70的学生，且去除重复数据");
        //filter+distinct筛选成绩大于等于70的学生，且去除重复数据
        students.stream().filter(student -> student.getScore() >= 70).distinct().forEach(System.out::println);

        System.out.println("limit最多截取前2个数据");
        //limit最多截取前2个数据
        students.stream().filter(student -> student.getScore() >= 70).limit(2).forEach(System.out::println);

        System.out.println("skip丢弃前2个数据");
        //skip丢弃前2个数据
        students.stream().filter(student -> student.getScore() >= 70).skip(2).forEach(System.out::println);

        System.out.println("skip丢弃前1个数据，limit最多截取前1个数据");
        students.stream().filter(student -> student.getScore() >= 70).skip(1).limit(1).forEach(System.out::println);
    }

    static class Student {
        private int age;
        private int score;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public Student(int age, int score, String name) {
            this.age = age;
            this.score = score;
            this.name = name;
        }

        public Student(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", score=" + score +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Student)) {
                return false;
            }

            Student student = (Student) o;

            if (getAge() != student.getAge()) {
                return false;
            }
            if (getScore() != student.getScore()) {
                return false;
            }
            return getName() != null ? getName().equals(student.getName()) : student.getName() == null;
        }

        @Override
        public int hashCode() {
            int result = getAge();
            result = 31 * result + getScore();
            result = 31 * result + (getName() != null ? getName().hashCode() : 0);
            return result;
        }
    }

}

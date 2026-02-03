package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * @author lx
 */
public class MappingTest {
    /**
     * mapping 类似于 map + collect
     */
    @Test
    public void mappingTest() {
        // 收集全部学生分数ArrayList集合
        System.out.println(students.stream().collect(mapping(Student::getScore, toList())));
        // 收集全部学生分数HashSet集合
        System.out.println(students.stream().collect(mapping(Student::getScore, toSet())));
        // 收集全部学生分数LinkedHashSet集合
        LinkedHashSet<Integer> collect =
            students.stream().collect(mapping(Student::getScore, toCollection(LinkedHashSet::new)));
        System.out.println(collect);

        // 收集全部学生分数的总和
        System.out.println(students.stream().collect(mapping(Student::getScore, reducing(0, Integer::sum))));
    }

    /**
     * collectingAndThen
     */
    @Test
    public void collectingAndThenTest() {
        // 收集全部学生总数
        Integer collect = students.stream().collect(collectingAndThen(toList(), List::size));
        System.out.println(collect);

        // 收集全部学生分数的总和
        Integer collect2 = students.stream()
            .collect(collectingAndThen(mapping(Student::getScore, reducing(Integer::sum)), Optional::get));
        System.out.println(collect2);
    }

    List<Student> students = new ArrayList<>();

    public void before() {
        students.add(new Student(1, 55, "小花"));
        students.add(new Student(2, 100, "小华"));
        students.add(new Student(3, 85, "晓华"));
        students.add(new Student(4, 70, "肖华"));
        students.add(new Student(5, 70, "小小"));
        students.add(new Student(6, 66, "小小"));
        students.add(new Student(7, 60, "小夏"));
        students.add(new Student(8, 77, "花花"));
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

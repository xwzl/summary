package com.turing.java.jvm.java8.stream.collect;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author lx
 */
public class CollectPartitioning {

    @Test
    public void partitioning() {
        // 根据学生成绩是否大于等于80进行分区
        Map<Boolean, List<Student>> collect =
            students.stream().collect(partitioningBy(student -> student.getScore() >= 80));
        System.out.println(collect);

        // 根据学生成绩是否大于等于80进行分区，输出学生名字
        Map<Boolean, List<String>> collect1 = students.stream()
            .collect(partitioningBy(student -> student.getScore() >= 80, mapping(Student::getName, toList())));
        System.out.println(collect1);

        // 根据学生成绩是否大于等于80进行分区，输出学生id-名字map
        Map<Boolean, Map<Integer, String>> collect2 = students.stream()
            .collect(partitioningBy(student -> student.getScore() >= 80, toMap(Student::getId, Student::getName)));
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

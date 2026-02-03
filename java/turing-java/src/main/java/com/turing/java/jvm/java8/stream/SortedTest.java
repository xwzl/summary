package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 */
public class SortedTest {

    private static int compare(Student o1, Student o2) {
        return o2.getScore() - o1.getScore();
    }

    public void before() {
        students.add(new Student(2, 100, "小花"));
        students.add(new Student(1, 100, "小华"));
        students.add(new Student(3, 85, "晓华"));
        students.add(new Student(4, 70, "肖华"));
    }

    /**
     * 自然排序
     */
    @Test
    public void sorted() {
        Stream.of(2, 0, 3, 7, 5).sorted().forEach(System.out::println);
    }

    /**
     * 指定比较规则
     */
    @Test
    public void sortedCom() {
        students.stream()
            // 首先根据score比较排序，相等时再根据id比较排序
            .sorted(Comparator.comparingInt(Student::getScore).thenComparing(Student::getId))
            .forEach(System.out::println);
    }

    List<Student> students = new ArrayList<>();

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
            return "Student{" + "id=" + id + ", score=" + score + ", name='" + name + '\'' + '}';
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

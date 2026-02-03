package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lx
 */
public class MatchTest {

    List<Student> students = new ArrayList<>();

    public void before() {
        students.add(new Student(1, 55, "小花"));
        students.add(new Student(2, 100, "小华"));
        students.add(new Student(3, 85, "晓华"));
        students.add(new Student(4, 70, "肖华"));
    }


    /**
     * 空流测试
     */
    @Test
    public void testNull() {
        System.out.println(Stream.of().anyMatch(i -> true));
        System.out.println(Stream.of().anyMatch(i -> false));
        System.out.println(Stream.of().noneMatch(i -> true));
        System.out.println(Stream.of().noneMatch(i -> false));
        System.out.println(Stream.of().anyMatch(i -> true));
        System.out.println(Stream.of().anyMatch(i -> false));
    }

    /**
     * match测试
     */
    @Test
    public void testMatch() {
        //集合中是否有姓名为 小华 的学生
        System.out.println(students.stream().anyMatch(student -> "小华".equals(student.getName())));
        //集合中是否有姓名为 小华1 的学生
        System.out.println(students.stream().anyMatch(student -> "小华1".equals(student.getName())));

        //集合中是否所有的学生分数都大于55
        System.out.println(students.stream().allMatch(student -> student.getScore() > 55));
        //集合中是否所有的学生分数都大于等于55
        System.out.println(students.stream().allMatch(student -> student.getScore() >= 55));

        //集合中是否所有的学生分数都不小于55
        System.out.println(students.stream().noneMatch(student -> student.getScore() < 55));
        //集合中是否所有的学生分数都不小于等于55
        System.out.println(students.stream().noneMatch(student -> student.getScore() <= 55));
    }

    @Test
    public void find() {
        Optional<String> first = Stream.of("xx", "ccc", "eee").findFirst();
        Optional<String> any = Stream.of("xx", "ccc", "eee").findAny();

        //通常我们这样使用
        //如果first存在
        if (first.isPresent()) {
            String s = first.get();
        }
        //如果any存在
        if (any.isPresent()) {
            String s = any.get();
        }

        //或者这样用
        first.ifPresent(System.out::println);
    }

    @Test
    public void optional() {
        System.out.println("filter");
        System.out.println(Optional.of(3).filter(integer -> integer > 2));
        System.out.println(Optional.of(3).filter(integer -> integer > 3));

        System.out.println("map");
        System.out.println(Optional.of(3).map(integer -> integer + 2));
        System.out.println(Optional.of(2).map(integer -> integer + 2));

        System.out.println("flatMap");
        System.out.println(Optional.of(3).flatMap(integer -> Optional.of(integer + 2)));
        System.out.println(Optional.of(2).flatMap(integer -> Optional.of(integer + 2)));
        //map对比，可以看到和Stream的flatMap与map方法差不多
        System.out.println(Optional.of(2).map(integer -> Optional.of(integer + 2)));
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
            return "Student{" +
                    "age=" + id +
                    ", score=" + score +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;

            Student student = (Student) o;

            if (getId() != student.getId()) return false;
            if (getScore() != student.getScore()) return false;
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

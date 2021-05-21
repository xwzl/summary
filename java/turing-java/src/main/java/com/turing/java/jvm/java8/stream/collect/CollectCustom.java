package com.turing.java.jvm.java8.stream.collect;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author lx
 */
public class CollectCustom {
    @Test
    public void test() {
        //使用toList
        List<Student> collect1 = students.stream().collect(Collectors.toList());
        System.out.println(collect1);

        //自定义收集器完成toList的功能
        List<Student> collect = students.stream().collect(Collector.of(ArrayList::new, List::add, (x, y) -> {
            x.addAll(y);
            return x;
        }));
        System.out.println(collect);

        //实际上toList方法的源码和此自定义收集器差不多：
        //new CollectorImpl<>((Supplier<List< T >>) ArrayList::new, List::add,(left, right) -> { left.addAll(right); return left; },CH_ID);


        //通常Collects提供的预定义收集器都能满足业务
        //自定义收集器一般用于完成一些比较独特的业务，比如在收集学生的时候对学生进行评分
        Function<Student, Integer> function = o -> {
            int score = o.getScore();
            if (score >= 90) {
                return 1;
            } else if (score >= 70) {
                return 2;
            } else {
                return 3;
            }
        };
        List<Student> collect2 = students.stream().collect(Collector.of(ArrayList::new, (x, y) -> {
            y.setGrade(function.apply(y));
            x.add(y);
        }, (x, y) -> {
            x.addAll(y);
            return x;
        }));
        System.out.println(collect2);


    }

    static List<Student> students = new ArrayList<>();

    @Before
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
        private Integer grade;

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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
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
                    "id=" + id +
                    ", score=" + score +
                    ", name='" + name + '\'' +
                    ", grade=" + grade +
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

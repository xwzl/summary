package com.turing.java.jvm.java8.stream.collect;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

import static java.util.stream.Collectors.*;

/**
 * 分区可以看作分组的特例，相比于分组操作可以将流元素分成多组，分区操作最多将流元素分成两个区域。某些时候我们需要将流元素根据是否满足某个或
 * 者某些条件来将元素分成两部分，这时就可以使用分区操作了。
 *
 * @author xuweizhi
 */
public class CollectGrouping {

    /**
     * groupingBy 一个参数
     */
    @Test
    public void groupingByOne() {

        // 对不同等级的学生进行收集分组
        Map<Integer, List<Student>> gradeMap = students.stream().collect(groupingBy(Student::getGrade));
        System.out.println(gradeMap);

        // 对不同分数的学生进行收集分组，自定义分组规则
        Map<Integer, List<Student>> collect = students.stream().collect(groupingBy(
            // 通过函数自定义分组规则
            student -> {
                int score = student.getScore();
                if (score >= 90) {
                    return 1;
                } else if (score >= 70) {
                    return 2;
                } else {
                    return 3;
                }
            }));
        System.out.println(collect);
    }

    /**
     * groupingBy 两个参数 第二个Collector参数可以实现各种收集逻辑
     */
    @Test
    public void groupingByTwo() {
        // 对不同等级的学生进行收集分组，使用List收集value元素，实际上就是上面单参数groupingBy内部调用的方法
        Map<Integer, List<Student>> collectList = students.stream().collect(groupingBy(Student::getGrade, toList()));
        System.out.println(collectList);
        System.out.println("=============");

        // 对不同等级的学生进行收集分组，使用ArrayList收集元素
        Map<Integer, ArrayList<Student>> collectArrayList =
            students.stream().collect(groupingBy(Student::getGrade, toCollection(ArrayList::new)));
        System.out.println(collectArrayList);
        System.out.println("=============");

        // 对不同等级的学生进行收集分组，使用HashSet收集元素
        Map<Integer, HashSet<Student>> collectSet =
            students.stream().collect(groupingBy(Student::getGrade, toCollection(HashSet::new)));
        System.out.println(collectSet);

        System.out.println("=============");

        // 对不同等级的学生进行收集分组，使用Map收集同组学生的id-name
        Map<Integer, Map<Integer, String>> collect =
            students.stream().collect(groupingBy(Student::getGrade, toMap(Student::getId, Student::getName)));
        System.out.println(collect);
        System.out.println("=============");

        // 对不同等级的学生进行收集分组，使用Integer收集同组学生的人数
        Map<Integer, Long> collectCounting = students.stream().collect(groupingBy(Student::getGrade, counting()));
        System.out.println(collectCounting);
        System.out.println("=============");
        // 对不同等级的学生进行收集分组，使用TreeMap收集同组学生的id-name并根据id大小倒序排序
        Map<Integer, TreeMap<Integer, String>> collect1 =
            students.stream().collect(groupingBy(Student::getGrade, toMap(Student::getId, Student::getName, (x, y) -> x,
                () -> new TreeMap<>(Comparator.comparingInt(o -> (int)o).reversed()))));
        System.out.println(collect1);
    }

    /**
     * groupingBy 三个参数 多了一个可以指定外层Map类型的参数
     */
    @Test
    public void groupingByThr() {
        // 对不同等级的学生进行收集分组，外层使用TreeMap排序
        TreeMap<Integer, List<Student>> collectTreeMap =
            students.stream().collect(groupingBy(Student::getGrade, TreeMap::new, toList()));
        System.out.println(collectTreeMap);

    }

    /**
     * groupingByConcurrent 一个参数，和groupingBy差不多，区别就是外层Map是安全的
     */
    @Test
    public void groupingByConcurrentByOne() {
        // 对不同等级的学生进行收集分组
        ConcurrentMap<Integer, List<Student>> gradeMap =
            students.stream().collect(groupingByConcurrent(Student::getGrade));
        System.out.println(gradeMap);

        // 对不同分数的学生进行收集分组，自定义分组规则
        ConcurrentMap<Integer, List<Student>> collect = students.stream().collect(groupingByConcurrent(
            // 通过函数自定义分组规则
            student -> {
                int score = student.getScore();
                if (score >= 90) {
                    return 1;
                } else if (score >= 70) {
                    return 2;
                } else {
                    return 3;
                }
            }));
        System.out.println(collect);
    }

    /**
     * groupingByConcurrent 两个参数，和groupingBy差不多，区别就是外层Map是安全的
     */
    @Test
    public void groupingByConcurrentByTwo() {
        // 对不同等级的学生进行收集分组
        ConcurrentMap<Integer, List<Student>> gradeMap =
            students.stream().collect(groupingByConcurrent(Student::getGrade));
        System.out.println(gradeMap);

        // 对不同分数的学生进行收集分组，自定义分组规则
        ConcurrentMap<Integer, List<Student>> collect = students.stream().collect(groupingByConcurrent(
            // 通过函数自定义分组规则
            student -> {
                int score = student.getScore();
                if (score >= 90) {
                    return 1;
                } else if (score >= 70) {
                    return 2;
                } else {
                    return 3;
                }
            }));
        System.out.println(collect);
    }

    /**
     * grouping或者groupingByConcurrent结合mapping使用，可以完成各种强大的，几乎满足所有业务需求的功能
     */
    @Test
    public void groupMapping() {

        // 对不同等级的学生进行收集分组，使用ArrayList收集同组学生的name
        Map<Integer, List<Integer>> collectName =
            students.stream().collect(groupingBy(Student::getGrade, mapping(Student::getId, toList())));
        System.out.println(collectName);

        // 对不同等级的学生进行收集分组，使用Integer收集同组学生的分数的和
        Map<Integer, Integer> collectSum = students.stream()
            .collect(groupingBy(Student::getGrade, mapping(Student::getId, reducing(0, Integer::sum))));
        System.out.println(collectSum);

        System.out.println(collectSum);
    }

    List<Student> students = new ArrayList<>();

    public void before() {
        students.add(new Student(1, 55, "小花", 4));
        students.add(new Student(2, 100, "小华", 1));
        students.add(new Student(3, 85, "晓华", 2));
        students.add(new Student(4, 70, "肖华", 2));
        students.add(new Student(5, 70, "小小", 2));
        students.add(new Student(6, 66, "小小", 3));
        students.add(new Student(7, 60, "小夏", 3));
        students.add(new Student(8, 77, "花花", 3));
    }

    static class Student {
        private int id;
        private int score;
        private String name;
        private int grade;

        public Integer getId() {
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

        public Student(int id, int score, String name, int grade) {
            this.id = id;
            this.score = score;
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" + "id=" + id + ", score=" + score + ", name='" + name + '\'' + ", grade=" + grade + '}';
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
            if (getGrade() != student.getGrade())
                return false;
            return getName() != null ? getName().equals(student.getName()) : student.getName() == null;
        }

        @Override
        public int hashCode() {
            int result = getId();
            result = 31 * result + getScore();
            result = 31 * result + (getName() != null ? getName().hashCode() : 0);
            result = 31 * result + getGrade();
            return result;
        }
    }
}

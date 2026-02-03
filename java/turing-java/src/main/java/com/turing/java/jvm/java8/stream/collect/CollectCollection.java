package com.turing.java.jvm.java8.stream.collect;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static java.util.stream.Collectors.*;

/**
 * @author lx
 */
public class CollectCollection {


    /**
     * collection
     */
    @Test
    public void collection() {
        //收集全部学生分数ArrayList集合
        List<Integer> scoreArrayList = students.stream().map(Student::getScore).collect(toList());
        //收集全部学生分数HashSet集合
        Set<Integer> scoreHashSet = students.stream().map(Student::getScore).collect(toSet());
        //收集全部学生分数LinkedHashSet集合
        Set<Integer> scoreLinkedHashSet = students.stream().map(Student::getScore).collect(toCollection(LinkedHashSet::new));

        System.out.println(scoreArrayList);
        System.out.println(scoreHashSet);
        System.out.println(scoreHashSet);
    }

    /**
     * map
     */
    @Test
    public void map() {

        //public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
        //注意：如果有重复的key，那么将抛出IllegalStateException异常

        //收集全部学生分数 名字-分数 的HashMap，将会抛出异常
        //Map<String, Integer> nameStoreHashMap = students.stream().collect(toMap(Student::getName, Student::getScore));

        //收集全部学生分数 id-分数 的HashMap，不会抛出异常
        Map<Integer, Integer> idStoreHashMap = students.stream().collect(toMap(Student::getId, Student::getScore));

        System.out.println(idStoreHashMap);


        //public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator< U > mergeFunction)
        //指定key冲突解决策略

        //收集全部学生分数 名字-分数 的HashMap，取前一个冲突的value
        Map<String, Integer> nameStoreHashMap1 = students.stream().collect(toMap(Student::getName, Student::getScore, (x
                , y) -> {
            System.out.println(x);
            System.out.println(y);
            return x;
        }));
        //收集全部学生分数 名字-分数 的HashMap，取后一个冲突的value
        Map<String, Integer> nameStoreHashMap2 = students.stream().collect(toMap(Student::getName, Student::getScore, (x
                , y) -> {
            System.out.println(x);
            System.out.println(y);
            return y;
        }));

        System.out.println(nameStoreHashMap1);
        System.out.println(nameStoreHashMap2);



        //public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator< U > mergeFunction, Supplier< M > mapSupplier)
        //指定key冲突解决策略以及指定Map类型LinkedHashMap
        LinkedHashMap<Integer, String> scoreNameLinkedHashMap = students.stream()
                .collect(toMap(Student::getScore, Student::getName, (x, y) -> y, LinkedHashMap::new));
        //指定key冲突解决策略以及指定Map类型，TreeMap
        TreeMap<Integer, String> scoreNameTreeMap = students.stream()
                .collect(toMap(Student::getScore, Student::getName, (x, y) -> y, TreeMap::new));

        System.out.println(scoreNameLinkedHashMap);
        System.out.println(scoreNameTreeMap);
    }


    /**
     * ConcurrentMap
     */
    @Test
    public void concurrentMap() {

        //public static <T,K,U> Collector<T,?,ConcurrentMap<K,U>> toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
        //注意：如果有重复的key，那么将抛出IllegalStateException异常

        //收集全部学生分数 名字-分数 的HashMap，将会抛出异常
        //ConcurrentMap<String, Integer> collect = students.stream().collect(toConcurrentMap(Student::getName,Student::getScore));

        //收集全部学生分数 id-分数 的ConcurrentHashMap，不会抛出异常
        ConcurrentMap<Integer, Integer> idStoreHashMap = students.stream().collect(toConcurrentMap(Student::getId, Student::getScore));

        System.out.println(idStoreHashMap);


        //public static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator< U > mergeFunction)
        //指定key冲突解决策略

        //收集全部学生分数 名字-分数 的ConcurrentHashMap，取前一个冲突的value
        ConcurrentMap<String, Integer> nameStoreHashMap1 = students.stream().collect(toConcurrentMap(Student::getName, Student::getScore, (x
                , y) -> {
            System.out.println(x);
            System.out.println(y);
            return x;
        }));
        //收集全部学生分数 名字-分数 的HashMap，取后一个冲突的value
        ConcurrentMap<String, Integer> nameStoreHashMap2 = students.stream().collect(toConcurrentMap(Student::getName, Student::getScore, (x
                , y) -> {
            System.out.println(x);
            System.out.println(y);
            return y;
        }));

        System.out.println(nameStoreHashMap1);
        System.out.println(nameStoreHashMap2);



        //public static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator< U > mergeFunction, Supplier< M > mapSupplier)
        //指定key冲突解决策略以及指定Map类型ConcurrentHashMap
        ConcurrentMap<Integer, String> scoreNameConcurrentHashMap = students.stream()
                .collect(toConcurrentMap(Student::getScore, Student::getName, (x, y) -> y, ConcurrentHashMap::new));
        //指定key冲突解决策略以及指定Map类型，ConcurrentSkipListMap
        ConcurrentMap<Integer, String> scoreNameConcurrentSkipListMap = students.stream()
                .collect(toConcurrentMap(Student::getScore, Student::getName, (x, y) -> y, ConcurrentSkipListMap::new));

        System.out.println(scoreNameConcurrentHashMap);
        System.out.println(scoreNameConcurrentSkipListMap);
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

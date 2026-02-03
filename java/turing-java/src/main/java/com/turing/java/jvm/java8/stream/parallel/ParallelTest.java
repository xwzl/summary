package com.turing.java.jvm.java8.stream.parallel;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 */
public class ParallelTest {


    @Test
    public void test1() {
        System.out.println("串行流");
        //串行流
        System.out.println(Stream.of("s", "qq", ";;", ".", ".", ".", ".").reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));

    }

    @Test
    public void test2() {
        System.out.println("parallel并行流");
        //添加parallel方法，变成并行流
        System.out.println(Stream.of("s", "qq", ";;", ".", ".", ".", ".").parallel().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));

    }


    @Test
    public void test7() {
        System.out.println("串行流");
        Optional<Integer> reduce = Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream().reduce((x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        });
        reduce.ifPresent(System.out::println);
    }


    @Test
    public void test8() {
        System.out.println("parallelStream并行流");
        Optional<Integer> reduce = Arrays.asList(1, 2, 3, 4, 5, 6, 7).parallelStream().reduce((x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        });
        reduce.ifPresent(System.out::println);
    }


    @Test
    public void test3() {
        System.out.println("串行流");

        //串行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").stream().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));
    }


    @Test
    public void test4() {
        System.out.println("parallelStream并行流");
        //并行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").parallelStream().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));
    }


    @Test
    public void test30() {
        System.out.println("串行流");

        //串行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").stream().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }, (x, y) -> x + y));
    }

    @Test
    public void test31() {
        System.out.println("并行流");

        //串行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").parallelStream().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }, (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y + x;
        }));
    }


    @Test
    public void test5() {
        System.out.println("sequential串行流");
        //串行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").parallelStream().sequential().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));
    }

    @Test
    public void test6() {
        System.out.println("sequential串行流");

        //串行流
        System.out.println(Arrays.asList("s", "qq", ";;", ".", ".", ".", ".").stream().parallel().sequential().reduce("-", (x, y) -> {
            System.out.println(Thread.currentThread().getName());
            return x + y;
        }));
    }
}

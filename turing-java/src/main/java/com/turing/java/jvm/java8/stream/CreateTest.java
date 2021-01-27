package com.turing.java.jvm.java8.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 */
public class CreateTest {


    static class Filter {
        private int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public Filter(int x) {
            this.x = x;
        }

        public Filter() {}

        @Override
        public String toString() {
            return "Filter{" + "x=" + x + '}';
        }
    }

    /**
     * 从集合获取流
     */
    @Test
    public void test() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter(0));
        filters.add(new Filter(3));
        filters.add(new Filter(9));
        filters.add(new Filter(8));
        // 从集合
        Stream<Filter> stream = filters.stream();
        stream.forEach(System.out::println);
    }

    /**
     * 从数组获取流
     */
    @Test
    public void test1() {
        Filter[] filArr = new Filter[] {new Filter(1), new Filter(3), new Filter(9), new Filter(8)};

        // 从数组
        Stream<Filter> stream = Arrays.stream(filArr);
        stream.forEach(System.out::println);
    }

    /**
     * 从文件获取流
     */
    @Test
    public void test2() {
        // 读取文件的所有行的流
        try (Stream<String> lines = Files.lines(Paths.get("target/classes/lines.txt"))) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * iterate获取10个偶数
     */
    @Test
    public void iterate() {
        Stream.iterate(0, n -> n + 2)
            // 取前十个数据（后面会讲，这叫做“筛选”）
            .limit(10).forEach(System.out::println);

    }

    /**
     * iterate获取下标 采用Stream通过下标遍历集合
     */
    @Test
    public void iterate2() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(3);
        objects.add(2);
        objects.add(4);
        Stream.iterate(0, i -> i + 1)
            // 截取前集合长度的数据
            .limit(objects.size()).forEach(i -> System.out.println(i + "-->" + objects.get(i)));

    }

    /**
     * generate获取10个随机数
     */
    @Test
    public void generate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    // 复杂数据生成

    /**
     * iterate获取10个斐波那契数
     */
    @Test
    public void iterateFibonacci() {
        // 斐波那契数列的规律：F(0)=0，F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
        // 我们初始化数组new int[]{0, 1}
        // 后续的数组的第1个元素是前一个数组的第2个元素，后续的数组的第2个元素是前一个数组的第1、2个元素的和
        // 这样实际上生成的数组如下:
        // new int[]{0, 1}
        // new int[]{1, 1}
        // new int[]{1, 2}
        // new int[]{2, 3}
        // new int[]{3, 5}
        // new int[]{5, 8}
        // new int[]{8, 13}
        // new int[]{13, 21}
        // new int[]{21, 34}
        // 取每个数组的第一个元素就是斐波那契数

        Stream.iterate(new long[] {0, 1}, t -> new long[] {t[1], t[0] + t[1]})
            // 生成10个数组
            .limit(100)
            // 获取每个数组的第一个元素（后面会讲，这叫做“映射”）
            .map(t -> t[0]).forEach(System.out::println);
    }

    /**
     * Stream.of
     */
    @Test
    public void of() {
        Stream.of(1, 2, 3, "11").forEach(System.out::println);
    }
}

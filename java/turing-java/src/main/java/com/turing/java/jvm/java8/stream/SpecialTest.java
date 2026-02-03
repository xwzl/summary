package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author xuweizhi
 */
public class SpecialTest {
    @Test
    public void test() {
        // 求和
        System.out.println(Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).sum());
        // 求最大值
        Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).max().ifPresent(System.out::println);
        // 求最小值
        Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).min().ifPresent(System.out::println);
        // 求算术平均数
        Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).average().ifPresent(System.out::println);
    }

    /**
     * 计数操作
     */
    @Test
    public void count() {
        // 前面我们讲过三种计数操作
        // Stream的count方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).count());
        // 特性化流的count方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).count());
        // map+reduce实现计数
        System.out.println(Stream.of(1, 2, 3, 4, 7).map(d -> 1).reduce(0, Integer::sum));

        // 现在，collect操作也提供计数功能，并且已经提供了预定于的收集器
        // counting()静态方法就返回一个用于计数的收集器
        Long collect = Stream.of(1, 2, 3, 4, 7).collect(counting());
        System.out.println(collect);
    }

    /**
     * 最值
     */
    @Test
    public void max_min() {
        // 前面我们讲过二种最值操作

        // 特性化流的方法
        Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).max().ifPresent(System.out::println);
        // reduce方法
        Optional<Integer> maxReduce = Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::max来表示求最值的意图
            .reduce(Integer::max);
        maxReduce.ifPresent(System.out::println);

        // 现在，collect操作也提供求最值功能，并且已经提供了预定于的收集器

        // minBy(Comparator) 静态方法就返回一个用于求最小值的收集器
        Stream.of(1, 2, 3, 4, 7).collect(minBy(Integer::compareTo)).ifPresent(System.out::println);
        // maxBy(Comparator) 静态方法就返回一个用于求最大值的收集器
        Stream.of(1, 2, 3, 4, 7).collect(maxBy(Integer::compareTo)).ifPresent(System.out::println);
        Stream.of(1, 2, 3, 4, 7).collect(maxBy(Comparator.comparingInt(x -> x))).ifPresent(System.out::println);
    }

    /**
     * 汇总
     */
    @Test
    public void sum() {
        // 前面我们讲过二种求和操作

        // 特性化流的方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).sum());
        // reduce方法
        Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::sum来表示求和的意图
            .reduce(Integer::sum).ifPresent(System.out::println);

        // 现在，collect操作也提供求和功能，并且已经提供了预定于的收集器

        // summingInt(ToIntFunction) 静态方法就返回一个用于求int数据和的收集器，返回int类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summingInt(x -> x)));

        // summingLong(ToLongFunction) 静态方法就返回一个用于求long数据和的收集器，返回long类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summingLong(x -> x)));

        // summingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据和的收集器，返回double类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summingDouble(x -> x)));

        // 现在，count操作还提供求平均数功能，并且已经提供了预定于的收集器

        // averagingInt(ToIntFunction) 静态方法就返回一个用于求int数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(averagingInt(x -> x)));

        // averagingLong(ToLongFunction) 静态方法就返回一个用于求long数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(averagingLong(x -> x)));

        // averagingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(averagingDouble(x -> x)));

        // summarizingInt(ToIntFunction) 静态方法就返回一个用于求int数据的总和、平均值、最大值和最小值的收集器
        // 返回IntSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summarizingInt(x -> x)));

        // summarizingLong(ToLongFunction) 静态方法就返回一个用于求long数据的总和、平均值、最大值和最小值的收集器
        // 返回LongSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summarizingLong(x -> x)));

        // summarizingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据的总和、平均值、最大值和最小值的收集器
        // 返回DoubleSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(summarizingDouble(x -> x)));
    }

    /**
     * 连接字符串
     */
    @Test
    public void string() {

        // join()
        System.out.println(Stream.of("校花", "小花", "晓华", "笑话").collect(joining()));
        System.out.println(String.join("", "校花", "小花", "晓华", "笑话"));

        // joining(CharSequence delimiter)
        System.out.println(Stream.of("校花", "小花", "晓华", "笑话").collect(joining("——")));
        System.out.println(String.join("——", "校花", "小花", "晓华", "笑话"));

        // joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        System.out.println(Stream.of("校花", "小花", "晓华", "笑话").collect(joining("——", "开始：", "。结束")));
    }

    /**
     * 自定义 看起来和reduce的功能差不多
     */
    @Test
    public void custom() {

        // 自定义求和操作
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(reducing(0, Integer::sum)));

        // 自定义计数操作
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(reducing(0, x -> 1, Integer::sum)));

        // 求最值，初始值10
        System.out.println(Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::max来表示求最值的意图
            .collect(reducing(10, Integer::max)));

        // 求最值
        Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::max来表示求最值的意图
            .collect(reducing(Integer::max)).ifPresent(System.out::println);

    }

}

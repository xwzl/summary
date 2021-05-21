package com.turing.java.jvm.java8.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class ReduceTest {

    /**
     * 求差 验证二元操作器的参数关系
     */
    @Test
    public void subtract() {
        // Optional< T > reduce(BinaryOperator< T > accumulator);
        // 求差 前一个计算的值减去后一个元素
        Optional<Integer> subtractReduce1 = Stream.of(1, 2, 3, 4, 7).reduce((i, j) -> i - j);
        subtractReduce1.ifPresent(System.out::println);

        // Optional< T > reduce(BinaryOperator< T > accumulator);
        // 求差 后一个元素减去前一个计算的值
        Optional<Integer> subtractReduce2 = Stream.of(1, 2, 3, 4, 7).reduce((i, j) -> j - i);
        subtractReduce2.ifPresent(System.out::println);
    }

    @Test
    public void test() {
        // Optional< T > reduce(BinaryOperator< T > accumulator);
        // 求和
        Optional<Integer> sumReduce = Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::sum来表示求和的意图
            .reduce(Integer::sum);
        sumReduce.ifPresent(System.out::println);

        // Optional< T > reduce(BinaryOperator< T > accumulator);
        // 求最值
        Optional<Integer> maxReduce = Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::max来表示求最值的意图
            .reduce(Integer::max);
        maxReduce.ifPresent(System.out::println);

        // T reduce(T identity, BinaryOperator< T > accumulator);
        // 求最值，初始值10
        System.out.println(Stream.of(1, 2, 3, 4, 7)
            // 使用方法引用Integer::max来表示求最值的意图
            .reduce(10, Integer::max));

        // T reduce(T identity, BinaryOperator< T > accumulator);
        // 计数
        System.out.println(Stream.of(1, 2, 3, 4, 7).map(d -> 1).reduce(0, Integer::sum));
        // 更简单的方法，内部使用的数值流，内部实际上是特性化的流操作，后面会讲
        System.out.println(Stream.of(1, 2, 3, 4, 7).count());
    }

    /**
     * < U > U reduce(U identity,BiFunction<U,? super T,U> accumulator,BinaryOperator< U > combiner) 复杂计算
     */
    @Test
    public void test2() {

        // 串行模式下无效
        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, Integer::sum, Integer::sum));
        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, Integer::sum, Integer::max));

        // 并行模式下有效
        // 初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：3，4，5，6，9，最后combiner整合这些值：得到27
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, Integer::sum, Integer::sum));
        // 初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：3，4，5，6，9，最后combiner整合这些值：得到9
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, Integer::sum, Integer::max));
        // 初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：1，2，3，4，7，最后combiner整合这些值：得到1
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(0, Integer::sum, Integer::min));

        // 初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：-1，0，1，2，5，最后combiner整合这些值：得到7
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> j - i, Integer::sum));
        // 初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：1，0，-1，-2，-5，最后combiner整合这些值：得到-7
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> i - j, Integer::sum));

        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> {
            // 查看线程，多线程
            System.out.println(Thread.currentThread().getName());
            return j - i;
        }, Integer::sum));

        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, (i, j) -> {
            // 查看线程，单线程
            System.out.println(Thread.currentThread().getName());
            return j - i;
        }, Integer::sum));
    }




}

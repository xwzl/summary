package com.turing.java.jvm.java8.stream.parallel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1. @author lx
 */
public class ParallelErr {

    /**
     * 求总和，long普通变量在串行模式下会丢失数据
     */
    @Test
    public void test() {
        Accumulator accumulator = new Accumulator();

        IntStream.rangeClosed(1, 10000).forEach(accumulator::add);
        System.out.println(accumulator);
    }

    /**
     * 求总和，long普通变量在并行模式下会丢失数据
     */
    @Test
    public void test1() {
        Accumulator accumulator = new Accumulator();
        IntStream.rangeClosed(1, 10000).parallel().forEach(accumulator::add);
        System.out.println(accumulator);
    }

    /**
     * 求总和，使用LongAdder累加器，这是一个线程安全的累加器
     */
    @Test
    public void test2() {
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 10000).parallel().forEach(longAdder::add);
        System.out.println(longAdder);
    }

    public static class Accumulator {
        public static int total = 0;

        public void add(int value) {
            total += value;
        }

        @Override
        public String toString() {
            return "" + total;
        }
    }

    /**
     * 要求：计算n的阶乘，然后将结果乘以5后返回
     */
    @Test
    public void test4() {
        System.out.println("安全的串行");

        // 5的阶乘再乘以5，然后返回
        // 串行模式下使用reduce没问题
        System.out.println(IntStream.rangeClosed(1, 5).reduce(5, (x, y) -> x * y));

        System.out.println("错误的并行");

        // 如果仅仅改成并行操作，那么就会出问题，最终计算结果为375000
        // 因为并行操作首先会将[1,5]之间的数拆分成为五组数据分别与5相乘，得到：5、10、15、20、25
        // 然后将结果继续汇总相乘：5*10=50、20*25=500、15 ，然后继续汇总相乘 15*500=7500、50，最后一次汇总：50*7500=375000
        System.out.println(IntStream.rangeClosed(1, 5).parallel().reduce(5, (x, y) -> {
            System.out.println("x：" + x + " y: " + y + " -> " + (x * y));
            return x * y;
        }));

        System.out.println("collectingAndThen改进");

        // 改成并行时，首先我们要保证初始值与其他之操作之后的值不会改变，因此这里的初始值只能是1，然后拆分相乘之后我们可以得到：1、2、3、4、5
        // 随后会将结果继续汇总相乘：1*2=2、4*5=20、3 ，然后继续汇总相乘 30*3=60、2，最后一次汇总：60*2=120
        // 这样我们就首先把5的阶乘求得了，最后是一个乘以5的操作，这一步我们必须保证不能并行
        // 因此使用collectingAndThen方法在最后调用乘以5即可，collectingAndThen可以保证最后一步是串行的
        Integer collect = IntStream.rangeClosed(1, 5).parallel().boxed()
            .collect(Collectors.collectingAndThen(Collectors.reducing(1, (x, y) -> x * y), x -> x * 5));
        System.out.println(collect);

        System.out.println("自定义收集器改进");
        // 当前我们也可以自定义收集器，同样满足我们的要求，而且性能更好

        // 完整版如下
        System.out
            .println(IntStream.rangeClosed(1, 5).parallel().boxed().collect(Collector.of(new Supplier<List<Integer>>() {

                /**
                 * 返回累加器函数
                 */
                @Override
                public List<Integer> get() {
                    return new ArrayList<>();
                }
            }, new BiConsumer<List<Integer>, Integer>() {
                /**
                 * 处理元素函数，添加到集合中
                 */
                @Override
                public void accept(List<Integer> integers, Integer integer) {
                    integers.add(integer);
                }
            }, new BinaryOperator<List<Integer>>() {
                /**
                 * 结果并行汇总函数 我们将两个集合的第一个元素相乘，然后替换第一个元素，最终结果就是1*2*3*4*5=120
                 */
                @Override
                public List<Integer> apply(List<Integer> integers, List<Integer> integers2) {
                    Integer integer = integers.get(0);
                    Integer integer1 = integers2.get(0);
                    integers.add(0, integer * integer1);
                    return integers;
                }
            }, new Function<List<Integer>, Integer>() {
                /**
                 * 返回最终结果函数 最后乘以5返回即可
                 */
                @Override
                public Integer apply(List<Integer> integers) {
                    return integers.get(0) * 5;
                }
            })));

        // 自定义收集器简化之后，这里也能看出来自定义收集器功能的强大
        System.out.println(IntStream.rangeClosed(1, 5).parallel().boxed()
            .collect(Collector.of(ArrayList::new, List::add, (integers, integers2) -> {
                integers.add(0, integers.get(0) * integers2.get(0));
                return integers;
            }, (Function<List<Integer>, Integer>)integers -> integers.get(0) * 5)));
    }
}

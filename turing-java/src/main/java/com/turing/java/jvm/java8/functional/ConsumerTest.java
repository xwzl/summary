package com.turing.java.jvm.java8.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {
        //一个初始化集合
        List<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        //对集合数据进行  加1然后输出的操作
        consume(objects, i -> System.out.println(i + 1));
    }

    /**
     * 使用Consumer对集合元素进行操作的方法
     *
     * @param list     需要操作的集合
     * @param consumer 对元素的具体的操作，在调用的时候传递某个动作就行了
     */
    private static <T> void consume(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}

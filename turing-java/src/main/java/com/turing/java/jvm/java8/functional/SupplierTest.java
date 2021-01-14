package com.turing.java.jvm.java8.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        //一个初始化集合
        List<Integer> objects = new ArrayList<>();
        //我们需要填充10个随机数，Supplier是一个获取随机数的动作
        Random random = new Random();
        supplier(objects, 10, () -> random.nextInt(10));
        //输出集合数据
        System.out.println(objects);
    }

    /**
     * 填充集合数据的方法
     *
     * @param list     需要填充的集合
     * @param count    需要填充的数量
     * @param supplier 获取数据的的操作，在调用的时候传递某个动作就行了
     */
    private static <T> void supplier(List<T> list, int count, Supplier<T> supplier) {
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
    }
}

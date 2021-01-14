package com.turing.java.jvm.java8.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        //一个初始化集合
        List<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        //对集合中的数据进行 自增1的操作
        function(objects, i -> ++i);
        //输出集合数据
        System.out.println(objects);
    }


    /**
     * 使用Function对集合元素进行操作的方法
     *
     * @param list     需要操作的集合
     * @param function 对元素的具体的函数操作，在调用的时候传递某个动作就行了
     */
    private static <T> void function(List<T> list, Function<T, T> function) {
        for (int i = 0; i < list.size(); i++) {
            //将通过传入的函数操作获取的结果替换原来的集合对应的数据
            list.set(i, function.apply(list.get(i)));
        }
    }
}

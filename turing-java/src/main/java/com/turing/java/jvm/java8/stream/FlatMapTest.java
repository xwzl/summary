package com.turing.java.jvm.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author xuweizhi
 */
public class FlatMapTest {

    List<String> words = new ArrayList<>();

    @Before
    public void before() {
        words.add("hello");
        words.add("word");
    }


    /**
     * 将words数组中的元素再按照字符拆分，然后字符去重，最终达到["h", "e", "l", "o", "w", "r", "d"]
     */
    @Test
    public void test2() {

        //如果用map，可以看到流就成了Stream<Stream<String>>类型，那么最后收集的元素就是Stream<String>类型
        List<Stream<String>> mapList = words.stream()
                .map(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(toList());
        System.out.println(mapList);


        //如果使用flatMap，那么就可以达到想要的结果
        List<String> flatMapList = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(toList());
        System.out.println(flatMapList);
    }

    /**
     * 原因
     */
    @Test
    public void test() {
        //可以看到Arrays.stream方法返回的就是Stream<String>类型
        Stream<String> stream = Arrays.stream("word".split(""));
        //如果使用map，那么返回的流就是一个Stream<Stream<String>>类型，流元素也是一个流……
        //因为map将Arrays.stream方法返回的Stream<String>作为流元素使用一个流进行收集，随后返回这个流
        Stream<Stream<String>> streamStream = words.stream().map(word -> Arrays.stream(word.split("")));
        //如果使用flatMap，那么返回的流就是一个Stream<String>类型，这才是正常的类型
        //因为flatMap将Arrays.stream方法返回的Stream<String>流进行了合并，随后返回合并之后的大流
        Stream<String> stringStream = words.stream().flatMap(word -> Arrays.stream(word.split("")));
    }
}

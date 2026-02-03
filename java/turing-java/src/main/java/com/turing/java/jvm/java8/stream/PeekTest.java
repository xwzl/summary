package com.turing.java.jvm.java8.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 */
public class PeekTest {
    @Test
    public void test() {
        System.out
            .println(Stream.of(1, 2, 3, 4, 5).peek(System.out::println).map(i -> i + 1).collect(Collectors.toList()));

        System.out.println(Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
            .peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
            .peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList()));
    }
}

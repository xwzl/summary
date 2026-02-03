package com.turing.java.util;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * LinkedList 测试双端队列实现
 *
 * @author xuweizhi
 * @since 2020/08/06 10:30
 */
public class LinkedListTest {

    @Test
    public void test() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
    }
}

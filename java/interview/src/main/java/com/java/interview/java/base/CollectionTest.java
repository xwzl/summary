package com.java.interview.java.base;

import lombok.ToString;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2021/05/10 17:50
 */
public class CollectionTest {

    public List<Node> source = new ArrayList<>();

    @Before
    public void init() {
        source.add(new Node("a1", 11));
        source.add(new Node("a1", 10));
        source.add(new Node("a1", 1));
        source.add(new Node("a1", 5));
        source.add(new Node("a2", 21));
        source.add(new Node("a2", 14));
        source.add(new Node("a3", 31));
        source.add(new Node("a3", 13));
        source.add(new Node("a3", 11));
    }

    @ToString
    public static class Node {
        private String code;
        private Integer score;

        public Node(String code, Integer score) {
            this.code = code;
            this.score = score;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    @Test
    public void sortTest() {
        source.stream().sorted(Comparator.comparing(Node::getCode, Comparator.reverseOrder()).thenComparing(Node::getScore, Comparator.reverseOrder())).forEach(System.out::println);
    }

    @Test
    public void testArrayList() {
        List<String> list = new ArrayList<>(1);
        list.add("test");
    }

    @Test
    public void numberValueOverflow() {
        int x = 2;
        while ((x - Integer.MAX_VALUE) < 0) {
            x += x >> 1;
            System.out.println(x);
        }
    }

    @Test
    @SuppressWarnings("all")
    public void concurrentModifyTest() throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("this");
        list.add("is");
        list.add("source");
        list.add("data");
        Thread t1 = new Thread(() -> {
            while (list.size() < 10) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("1");
            }
        }, "A");
        Thread t2 = new Thread(() -> {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(list);
                    e.printStackTrace();
                }
                System.out.println(iterator.next());
            }
        }, "B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

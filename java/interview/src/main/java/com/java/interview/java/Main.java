package com.java.interview.java;

import java.util.Random;

/**
 * @author xuweizhi
 * @since 2022/05/20 19:49
 */
public class Main {


    public static void main(String[] args) {
        Random rand = new Random();
        int time = rand.nextInt(20)+10;
        System.out.println("I will working for " + time + " second!");
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All work is done! Bye");
    }
}

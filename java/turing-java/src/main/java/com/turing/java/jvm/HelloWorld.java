package com.turing.java.jvm;

public class HelloWorld {

    public int add() {
        int a = 1;
        int b = 2;
        int c = (a * b) * 10;
        return c;
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.add();
    }
}

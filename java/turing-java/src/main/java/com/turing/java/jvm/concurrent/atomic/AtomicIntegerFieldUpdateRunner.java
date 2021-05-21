package com.turing.java.jvm.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdateRunner {

    static AtomicIntegerFieldUpdater<Student> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "old");

    public static void main(String[] args) {
        Student stu = new Student("杨过", 18);
        System.out.println(fieldUpdater.getAndIncrement(stu));
        System.out.println(fieldUpdater.getAndIncrement(stu));
        System.out.println(fieldUpdater.get(stu));
    }

    static class Student {
        private String name;
        public volatile int old;

        public Student(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }

}

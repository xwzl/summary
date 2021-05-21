package com.turing.java.interview.java.base;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * SPI 机制，服务提供接口
 *
 * @author xuweizhi
 * @since 2021/05/14 17:07
 */
public class ServiceProviderInterfaceExample {

    public static interface Animal {
        /**
         * 行为
         */
        void action();
    }

    public static class Dog implements Animal {
        @Override
        public void action() {
            System.out.println("How are you!");
        }
    }

    public static class Cat implements Animal {
        @Override
        public void action() {
            System.out.println("I'm good");
        }

        @Test
        public void spiTest() {
            ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
            for (Animal animal : serviceLoader) {
                animal.action();
            }
            System.out.println(Animal.class.getName());
        }
    }
}

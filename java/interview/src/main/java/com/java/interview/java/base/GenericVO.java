package com.java.interview.java.base;

import org.junit.Test;

/**
 * 泛型相关测试
 *
 * @author xuweizhi
 * @since 2021/04/28 15:48
 */
public class GenericVO {

    public static interface Info<T> {
        /**
         * 泛型测试
         *
         * @return 泛型测试
         */
        T getValue();

        /**
         * 提供默认返回值
         *
         * @param e 入参
         * @return 返回值
         */
        default <E extends T> E defaultReturn(E e) {
            System.out.println("默认返回值：");
            return e;
        }
    }

    public static class InfoImpl implements Info<String> {
        @Override
        public String getValue() {
            System.out.println("泛型接口实现");
            return "this is big gg";
        }


    }

    public static class InfoGeneric<T> implements Info<T> {

        @Override
        public T getValue() {
            System.out.println("泛型接口测试：");
            return null;
        }

    }


    public static class InfoTest {
        @Test
        public void infoMainTest() {
            Info<String> info = new InfoImpl();
            System.out.println(info.getValue());
        }
    }

    private static <T extends Number> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

}

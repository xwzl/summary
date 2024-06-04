package com.turing.java;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;


/**
 * -Dsun.misc.UnsafeEnabled=true
 */
public class UnsafeTest {

    private String kk;


    private Integer tat;

    public static Unsafe U = UnsafeUtils.getUnsafe();

    public static Class<?> unsafeTestClass = UnsafeTest.class;


    public static void main(String[] args) throws NoSuchFieldException {
        UnsafeTest unsafeTest = new UnsafeTest();
        Long kkMemoryAddress = getMemoryAddress("kk");
        Object objectVolatile = U.getObjectVolatile(unsafeTest, kkMemoryAddress);
        if (objectVolatile == null) {

            boolean b = U.compareAndSwapObject(unsafeTest, kkMemoryAddress, null, "test");
            System.out.println(U.getObjectVolatile(unsafeTest, kkMemoryAddress));
        }
    }

    public static Long getMemoryAddress(String fieldName) {

        try {
            return U.objectFieldOffset(unsafeTestClass.getDeclaredField("kk"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}

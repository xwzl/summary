package com.turing.java;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * -Dsun.misc.UnsafeEnabled=true
 */
public class UnsafeTest {

    private String kk;


    private Integer tat;

    public static Unsafe U = UnsafeUtils.getUnsafe();

    public static Class<?> unsafeTestClass = UnsafeTest.class;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        //concurrentHashMap.put("1", null);

        UnsafeTest unsafeTest = new UnsafeTest();
        Long kkMemoryAddress = getMemoryAddress("kk");
        Object objectVolatile = U.getObjectVolatile(unsafeTest, kkMemoryAddress);
        if (objectVolatile == null) {
            boolean b = U.compareAndSwapObject(unsafeTest, kkMemoryAddress, null, "test");
            System.out.println(U.getObjectVolatile(unsafeTest, kkMemoryAddress));
        }

        String lv = "lv";
        Class<String> stringClass = String.class;
        Field value = stringClass.getDeclaredField("value");
        value.setAccessible(true);
        boolean b = U.compareAndSwapObject(lv, getMemoryAddress(stringClass, "value"), value.get(lv), new char[]{'1', '2'});
        System.out.println(lv.toString());
    }

    public static Long getMemoryAddress(String fieldName) {

        try {
            return U.objectFieldOffset(unsafeTestClass.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long getMemoryAddress(Class<?> unsafeTestClass, String fieldName) {

        try {
            return U.objectFieldOffset(unsafeTestClass.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.turing.java.jvm.loader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xuweizhi
 * @since 2020/09/29 14:45
 */
@Slf4j
public class UnSafeTest {

    private static Unsafe unsafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws NoSuchFieldException {
        long yOffset = unsafe.objectFieldOffset(NullPoint.class.getDeclaredField("y"));
        log.info("Null Point y offset field value:{}", yOffset);
        NullPoint nullPoint = new NullPoint();
        nullPoint.setY(158262751);
        Object object = unsafe.getObject(nullPoint, yOffset);
        log.info("Null Point y field value:{}", object);
    }
}

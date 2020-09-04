package com.turing.java.jvm.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * HashMap 测试
 *
 * @author xuweizhi
 * @since 2020/08/19 16:08
 */
@Slf4j
public class HashMapTest {


    @Test
    public void arrayTest() {
        List<String> a = new ArrayList<>(1);
        for (int i = 0; i < 20; i++) {
            a.add("i");
        }
    }

    /**
     * 低位与高位取模
     */
    @Test
    public void hashMapTest() {
        int hashCode1 = "1111".hashCode();
        log.info("hashcode1 value:{}", hashCode1);
        log.info("hashcode1 binary value:{}", Integer.toBinaryString(hashCode1));
        log.info("hashcode1 right shift:{}", Integer.toBinaryString(hashCode1 >>> 16));
        log.info("hashcode1 hash map key:{}", Integer.toBinaryString(hashCode1 ^ (hashCode1 >>> 16)));
        log.info("hashcode1 hash map key:{}", hashCode1 ^ (hashCode1 >>> 16));
        int hashCode2 = "2222".hashCode();
        log.info("hashcode2 value:{}", hashCode2);
        log.info("hashcode2 binary value:{}", Integer.toBinaryString(hashCode2));
        log.info("hashcode2 right shift:{}", Integer.toBinaryString(hashCode2 >>> 16));
        log.info("hashcode2 hash map key:{}", Integer.toBinaryString(hashCode2 ^ (hashCode2 >>> 16)));
        log.info("hashcode2 hash map key:{}", hashCode2 ^ (hashCode2 >>> 16));
    }

    @Test
    public void putTest() {
        HashMap<String, Integer> hashMap = new HashMap<>(16, 10f);
        for (int i = 0; i < 12200; i++) {
            hashMap.put(i + "", i);
        }
        System.out.println("");
    }

    @Test
    public void valueInitTest() {
        Random random = new Random();
        int i = 0;
        if ((i = random.nextInt(100)) > 50) {
            log.info("{}", i);
        } else if (i < 20) {
            log.info("{}", i);
        }
        log.info("{}", i);
    }
}

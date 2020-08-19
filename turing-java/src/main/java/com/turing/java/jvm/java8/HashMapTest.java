package com.turing.java.jvm.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * HashMap 测试
 *
 * @author xuweizhi
 * @since 2020/08/19 16:08
 */
@Slf4j
public class HashMapTest {

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
}

package com.turing.java.jvm.concurrent;

import com.turing.java.jvm.concurrent.utils.UnsafeInstance;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Cas 测试
 *
 * @author xuweizhi
 * @since 2020/09/23 13:42
 */
@Slf4j
public class CasExample {

    private volatile String cas;

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static long stateOffset;

    static {
        try {
            assert unsafe != null;
            // 类似于某个 CasExample 实例中，相对于当前实例 cas 的内存偏向值
            stateOffset = unsafe.objectFieldOffset(CasExample.class.getDeclaredField("cas"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static final String[] abaArray = {"A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        CasExample casExample = new CasExample();
        casExample.cas = "A";
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                for (; ; ) {
                    String oldCas = casExample.cas;
                    String newCas = abaArray[(int) (Math.random() * 6)];// 如何解决呢？
                    if (casExample.compareAndSetCas(oldCas, newCas)) {
                        log.info("olaCas:{},newCas:{}", oldCas, newCas);
                        Thread.yield();// 让出 cpu 执行权
                    }
                }
            }, String.format("线程: %s", i)));
        }
        for (Thread thread : list) {
            thread.start();
        }
    }

    public boolean compareAndSetCas(String old, String expect) {
        assert unsafe != null;
        // 相当于给 cas 变量赋值，this + stateOffset 对应该实例中 cas 字段的内存地址值
        return unsafe.compareAndSwapObject(this, stateOffset, old, expect);
    }
}

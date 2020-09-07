package com.turing.java.jvm.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁测试
 *
 * @author xuweizhi
 * @since 2020/09/07 14:54
 */
@Slf4j
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    int count = 0;

    @Test
    public void lockTest() throws InterruptedException {

    }

    private void lockAwait() throws InterruptedException {
        lock.lock();
        condition.await();
        log.info("count : {}", count++);
        lock.unlock();
    }
}

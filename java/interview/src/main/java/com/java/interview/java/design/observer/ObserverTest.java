package com.java.interview.java.design.observer;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

/**
 * 单元测试
 *
 * @author xuweizhi
 * @since 2022/04/25 23:49
 */
@Slf4j
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        Instant start = Instant.now();
        subject.registerObserver(event -> log.info("订单{}处理完成", event));
        subject.registerObserver(event -> log.info("库存{}处理完成", event));
        subject.notifyAllObserver("we are happy");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("执行耗时:{}", timeElapsed);
    }
}

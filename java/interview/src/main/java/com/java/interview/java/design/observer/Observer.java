package com.java.interview.java.design.observer;

/**
 * 观察者
 *
 * @author xuweizhi
 * @since 2022/04/25 23:43
 */
public interface Observer {

    /**
     * 通知事件
     *
     * @param event 事件
     */
    void notify(String event);
}

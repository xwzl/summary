package com.java.interview.java.design.observer;

/**
 * 订阅者
 *
 * @author xuweizhi
 * @since 2022/04/25 23:42
 */
public interface Subject {

    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 通知所有观察者
     *
     * @param event 事件
     */
    void notifyAllObserver(String event);
}

package com.java.interview.java.design.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2022/04/25 23:45
 */
public class SubjectImpl implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObserver(String event) {
        observers.forEach(observer -> observer.notify(event));
    }
}

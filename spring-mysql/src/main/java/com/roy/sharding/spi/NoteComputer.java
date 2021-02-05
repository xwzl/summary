package com.roy.sharding.spi;

/**
 * @author xuweizhi
 * @since 2021/02/05 10:44
 */
public class NoteComputer implements Computer {
    @Override
    public void description() {
        System.out.println("笔记本电脑");
    }
}

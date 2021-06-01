package com.java.consumer.consumer;


import com.java.common.module.providers.DemoServiceListener;

public class DemoServiceListenerImpl implements DemoServiceListener {

    @Override
    public void changed(String msg) {
        System.out.println("被回调了："+msg);
    }
}

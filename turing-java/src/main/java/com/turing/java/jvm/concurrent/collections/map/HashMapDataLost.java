package com.turing.java.jvm.concurrent.collections.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDataLost {
    public static final Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws InterruptedException {
        //线程一
        new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
            }
        }.start();
        //线程二
        new Thread(){
            public void run() {
                for(int j=1000;j<2000;j++){
                    map.put(String.valueOf(j), String.valueOf(j));
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("map.size"+map.size());
        //输出
        for(int i=0;i<2000;i++){
            System.out.println("第："+i+"元素，值："+map.get(String.valueOf(i)));
        }

    }
}

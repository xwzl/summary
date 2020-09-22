package com.turing.java.jvm.concurrent.sync;

public class LockAppend {

    StringBuffer stb = new StringBuffer();

    private void method() {
        stb.append("杨过");
        stb.append("小龙女");
        stb.append("大雕");
        stb.append("郭靖");
    }
}

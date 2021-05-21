package com.turing.java.jvm.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;


public class AtomicReferenceArrayRunner {

    static Turing[] oValue = new Turing[]{new Turing(1), new Turing(2)};
    static AtomicReferenceArray<Turing> objArray = new AtomicReferenceArray<>(oValue);

    public static void main(String[] args) {
        System.out.println(objArray.get(0).getSequence());
        objArray.set(0, new Turing(3));
        System.out.println(objArray.get(0).getSequence());
        System.out.println(oValue[0]);
    }

}

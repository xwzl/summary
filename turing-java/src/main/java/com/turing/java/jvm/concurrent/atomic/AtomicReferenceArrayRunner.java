package com.turing.java.jvm.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;


public class AtomicReferenceArrayRunner {

    static Turing[] ovalue = new Turing[]{new Turing(1),new Turing(2)};
    static AtomicReferenceArray<Turing> objarray = new AtomicReferenceArray(ovalue);

    public static void main(String[] args) {
        System.out.println(objarray.get(0).getSequence());

        objarray.set(0,new Turing(3));

        System.out.println(objarray.get(0).getSequence());

    }

}

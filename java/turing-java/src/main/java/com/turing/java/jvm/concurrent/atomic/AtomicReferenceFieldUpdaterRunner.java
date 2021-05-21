package com.turing.java.jvm.concurrent.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.UnaryOperator;


public class AtomicReferenceFieldUpdaterRunner {

    static final AtomicReferenceFieldUpdater<Document, String> atomic = AtomicReferenceFieldUpdater.newUpdater(Document.class,
            String.class, "name");

    public static void main(String[] args) {
        Document document = new Document("杨过", 1);
        System.out.println(atomic.get(document));
        atomic.getAndSet(document, "妹子");
        System.out.println(atomic.get(document));
        //另一种方式修改
        UnaryOperator<String> uo = s -> {
            System.out.println("UnaryOperator:-->" + s);
            return "小龙女";
        };
        System.out.println(atomic.getAndUpdate(document, uo));
        System.out.println(atomic.get(document));
    }

    @Data
    static class Document {
        public volatile String name;
        private int version;

        Document(String obj, int v) {
            name = obj;
            version = v;
        }

    }
}

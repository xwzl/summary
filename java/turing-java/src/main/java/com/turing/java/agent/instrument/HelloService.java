package com.turing.java.agent.instrument;


/**
 * @author xuweizhi
 */
public class HelloService {

    @Agent
    public String say(){
        System.out.println("===hello world====");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world";
    }

    @Agent
    public String say2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world";
    }

}

package com.turing.java.agent.instrument;

/**
 * @author xuweizhi
 */
public class AgentInstrumentTest {

    //-javaagent:/Users/xuweizhi/Documents/root/summary/java/java-agent/instrument/target/instrument-1.0.0.jar
    public static void main(String[] args) {
        HelloService helloService = new HelloService();
        helloService.say();
        helloService.say2();
    }
}

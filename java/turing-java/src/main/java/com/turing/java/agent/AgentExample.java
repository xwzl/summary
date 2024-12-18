package com.turing.java.agent;

/**
 * Java javassist
 *
 * @author xuweizhi
 * @since 2022/01/04 14:21
 */
public class AgentExample {

    // -javaagent:/Users/xuweizhi/Documents/projects/summary/java/java-agent/byte-buddy/target/byte-buddy-1.0.0.jar
    public static void main(String[] args) {

        HelloService helloService = new HelloService();
        helloService.say();
        helloService.say2();

        HelloService2 helloService2 = new HelloService2();
        helloService2.say();
        helloService2.say2();
    }

}

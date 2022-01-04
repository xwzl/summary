package com.summary.java.agent.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;


/**
 * @author xuweizhi
 */
public class AgentDemo {

    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("premain：获取方法调用时间");

        // 添加 Transformer
        ClassFileTransformer transformer = new PerformMonitorTransformer();
        instrumentation.addTransformer(transformer);
    }
}

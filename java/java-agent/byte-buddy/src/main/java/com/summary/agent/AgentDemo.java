package com.summary.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author xuweizhi
 */
public class AgentDemo {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain：获取方法调用时间");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                return builder
                        // 方法拦截
                        .method(ElementMatchers.nameStartsWith("say"))
                        .intercept(MethodDelegation.to(TimeInterceptor.class));
            }
        };

        new AgentBuilder.Default()
                // 拦截类
                .type(ElementMatchers.nameStartsWith("com.turing.java.agent"))
                .transform(transformer)
                .installOn(inst);
    }

}

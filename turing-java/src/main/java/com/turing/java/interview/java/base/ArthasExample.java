package com.turing.java.interview.java.base;

import lombok.ToString;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Arthas 测试
 *
 * @author xuweizhi
 * @since 2021/05/06 22:04
 */
@SuppressWarnings("all")
public class ArthasExample {

    private static InnerParam varTest = new InnerParam("init", 1);

    @ToString
    public static class InnerParam {
        private String strParam;
        private Integer intParam;

        public InnerParam(String strParam, Integer intParam) {
            this.strParam = strParam;
            this.intParam = intParam;
        }

        public void setStrParam(String strParam) {
            this.strParam = strParam;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        SecureRandom random = new SecureRandom();
        while ((str = scanner.nextLine()) != "exit") {
            switch (str) {
                case "param":
                    InnerParam innerParam = new InnerParam(str, random.nextInt(100));
                    printObj(innerParam);
                    break;
                case "exption":
                    throw new RuntimeException("异常测试");
                case "conditon":
                    //  满足则打印
                    //  watch com.turing.java.interview.java.base.ArthasExample printObj 'params[0].intParam > 30'
                    InnerParam condition = new InnerParam("str", 20);
                    printObj(condition);
                case "replace":
                    // sc -d com.turing.java.interview.java.base.ArthasExample | grep classLoaderHash
                    // sun.misc.Launcher$AppClassLoader@18b4aac2
                    // ognl -c 18b4aac2 @com.turing.java.interview.java.base.ArthasExample@varTest
                    // ognl -c 18b4aac2 @com.turing.java.interview.java.base.ArthasExample@varTest@setStrParam("111")
                    // 唯一实例
                    // ognl --classLoaderClass org.springframework.boot.loader.LaunchedURLClassLoader  @org.springframework.boot.SpringApplication@logger
                    System.out.println(varTest);
                default:
                    break;
            }
        }
    }

    /**
     * watch com.turing.java.interview.java.base.ArthasExample printObj '{loader,clazz,method,params, target,returnObj,isBefore,isThrow,isReturn,throwExp}'
     *
     * @param innerParam
     */
    private static InnerParam printObj(InnerParam innerParam) {
        System.out.println(innerParam);
        return innerParam;
    }

}

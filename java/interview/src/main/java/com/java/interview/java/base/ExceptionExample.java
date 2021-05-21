package com.java.interview.java.base;

import lombok.extern.java.Log;
import org.junit.Test;

import java.security.SecureRandom;

/**
 * 异常示例
 *
 * @author xuweizhi
 * @since 2021/04/30 10:17
 */
@Log
@SuppressWarnings("all")
public class ExceptionExample {


    public static class ExceptionTest {

        public Integer generateInt() {
            SecureRandom secureRandom = new SecureRandom();
            int result = secureRandom.nextInt(100);
            if (result > 50) {
                throw new RuntimeException("随机数生成异常" + result);
            }
            return result;
        }


        /**
         * 正确记录异常的姿势
         */
        @Test
        public void exceptionMethodTest() {
            Integer result;
            try {
                result = generateInt();
            } catch (Exception e) {
                //log.info(e.getMessage());
                throw new RuntimeException("业务异常", e);
            }

            System.out.println(String.format("随机数为 %d", result));
        }
    }

    public static class ExceptionTimeTest {

        private int testTimes;

        public ExceptionTimeTest() {
        }

        public void newObject() {
            long l = System.nanoTime();
            for (int i = 0; i < testTimes; i++) {
                new Object();
            }
            System.out.println("建立对象：" + (System.nanoTime() - l));
        }

        public void newException() {
            long l = System.nanoTime();
            for (int i = 0; i < testTimes; i++) {
                new Exception();
            }
            System.out.println("建立异常对象：" + (System.nanoTime() - l));
        }

        public void catchException() {
            long l = System.nanoTime();
            for (int i = 0; i < testTimes; i++) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                }
            }
            System.out.println("建立、抛出并接住异常对象：" + (System.nanoTime() - l));
        }

        /**
         * 建立一个异常对象，是建立一个普通Object耗时的约20倍（实际上差距会比这个数字更大一些，因为循环也占用了时间，追求精确的读者可以再测一下空循环的耗时然后在对比前减掉这部分），而抛出、接住一个异常对象，所花费时间大约是建立异常对象的4倍。
         */
        @Test
        public void timeTest() {
            ExceptionTimeTest test = new ExceptionTimeTest();
            test.testTimes=1000;
            test.newObject();
            test.newException();
            test.catchException();
        }
    }

}

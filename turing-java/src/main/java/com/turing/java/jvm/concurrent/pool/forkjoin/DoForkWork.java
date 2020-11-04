package com.turing.java.jvm.concurrent.pool.forkjoin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Slf4j
public class DoForkWork {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<?> submit = executor.submit(() -> {
            log.info("this woker start do work");
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject("https://www.baidu.com/", String.class, new Object[]{});
            System.out.println(forObject);
            Thread.sleep(5000);
            return 1;
        });

        // 发廊理发
        // 按摩
        // 看一下你的前女友
        // 异步阻塞

        try {
            System.out.println(submit.get(2,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("has got the result");
    }
}

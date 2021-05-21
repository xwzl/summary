package com.spring.zookeeper.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/hosaos/article/details/88727817
 * <p>
 * 分布式调度任务系统里，从可靠性角度出发，Master集群也是必不可少的。但往往，为了保证任务不会重复分配，分配任务的节点只能有一个，
 * 这种情况就需要从Master集群中选出一个Leader（老大）去任务池里取任务，本文就会介绍Curator基于Zookeeper封装的Leader选举工具类
 * LeaderLatch与LeaderSelector的使用及原理分析
 */
public class LeaderSelectorDemo {

    //         <dependency>
    //            <groupId>org.apache.curator</groupId>
    //            <artifactId>curator-recipes</artifactId>
    //            <version>5.0.0</version>
    //            <exclusions>
    //                <exclusion>
    //                    <groupId>org.apache.zookeeper</groupId>
    //                    <artifactId>zookeeper</artifactId>
    //                </exclusion>
    //            </exclusions>
    //        </dependency>
    //         <dependency>
    //            <groupId>org.apache.zookeeper</groupId>
    //            <artifactId>zookeeper</artifactId>
    //            <version>3.5.8</version>
    //        </dependency>
    private static final String CONNECT_STR = "192.168.109.200:2181";

    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000, 10);

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    // 项目依赖有问题，找一个项目单独执行
    public static void main(String[] args) throws InterruptedException {
        String appName = System.getProperty("appName");
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STR, retryPolicy);
        curatorFramework.start();
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            // 多个客户端同时请求，只有一个客户端能够成为主节点，进行真正的任务提交。
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println(" I' m leader now . i'm , " + appName);
                TimeUnit.SECONDS.sleep(15);
            }
        };
        LeaderSelector selector = new LeaderSelector(curatorFramework, "/cachePreHeat_leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
        countDownLatch.await();

    }
}

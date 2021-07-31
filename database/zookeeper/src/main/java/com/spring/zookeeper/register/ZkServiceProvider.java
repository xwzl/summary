package com.spring.zookeeper.register;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * ZK Service Provider
 *
 * @author xuweizhi
 * @since 2021/07/31 13:55
 */
@SuppressWarnings("all")
public class ZkServiceProvider implements Watcher {

    private static CountDownLatch connectionSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    private static String rootPath = "/GroupMembers";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper("localhost:2181", 5000, new ZkServiceProvider());
        connectionSemaphore.await();
        zk.create(rootPath + "/cl", "test create".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("创建集群节点成功");
        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectionSemaphore.countDown();
            }
        }
    }
}

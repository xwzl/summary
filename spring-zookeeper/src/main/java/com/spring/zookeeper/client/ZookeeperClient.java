package com.spring.zookeeper.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.zookeeper.utils.ZookeeperUtils;
import lombok.SneakyThrows;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType.NodeDataChanged;
import static org.apache.zookeeper.Watcher.Event.EventType.None;


/**
 * @author xuweizhi
 * @since 2020/11/25 10:47
 */

public class ZookeeperClient {

    private final static String CONNECTION_STR =  ZookeeperUtils.ip + ":2181";

    private final static int SESSION_TIMEOUT = 30 * 1000;

    private final static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static class ConnectionWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (getZookeeperConnectionState(watchedEvent)) {
                System.out.println("zookeeper is connection");
                countDownLatch.countDown();
            }
        }

        /**
         * zookeeper 链接客户端触发的的时间
         * <p>
         * WatchedEvent state:SyncConnected type:None path:null
         */
        private boolean getZookeeperConnectionState(WatchedEvent watchedEvent) {
            return watchedEvent.getState() == Event.KeeperState.SyncConnected && watchedEvent.getType() == None
                    && watchedEvent.getPath() == null;
        }
    }

    public static class ConfigWatcher implements Watcher {

        @SneakyThrows
        @Override
        public void process(WatchedEvent event) {
            if (event.getPath() != null && "/config".equals(event.getPath()) && event.getType() == NodeDataChanged) {
                System.out.printf("%s 路径原始数据发生了变化%n", event.getPath());
                byte[] data = zooKeeper.getData("/config", this, null);
                MyConfig myConfig = objectMapper.readValue(new String(data), MyConfig.class);
                System.out.println("变化数据为" + myConfig.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONNECTION_STR, SESSION_TIMEOUT, new ConnectionWatcher());
        System.out.println("Waiting zookeeper connection");
        countDownLatch.await();
        // 创建 create /config {"key":"key","name":"name"}
        byte[] data = zooKeeper.getData("/config", new ConfigWatcher(), null);
        MyConfig myConfig = objectMapper.readValue(new String(data), MyConfig.class);
        System.out.println("原始数据为" + myConfig.toString());
        Thread.sleep(Integer.MAX_VALUE);
    }
}

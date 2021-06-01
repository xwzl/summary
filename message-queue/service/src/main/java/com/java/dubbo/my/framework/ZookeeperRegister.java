package com.java.dubbo.my.framework;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * zookeeper 注册中心
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class ZookeeperRegister {

    static CuratorFramework client;

    static Map<String, List<URL>> urlCache = new ConcurrentHashMap<>();

    static {
        client = CuratorFrameworkFactory.newClient("localhost:2181", new RetryNTimes(3, 1000));
        client.start();
    }

    public static void register(String interfaceName, URL url) {
        try {
            String nodePath = String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url));
            if(client.getData().forPath(nodePath) != null){
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath(nodePath);
            }
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<URL> get(String interfaceName) {
        if (urlCache.containsKey(interfaceName)) {
            return urlCache.get(interfaceName);
        } else {
            List<URL> urlList = new ArrayList<>();
            try {
                List<String> result = client.getChildren().forPath(String.format("/dubbo/service/%s", interfaceName));
                for (String url : result) {
                    urlList.add(JSONObject.parseObject(url, URL.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            urlCache.put(interfaceName, urlList);
            return urlList;
        }
    }
}

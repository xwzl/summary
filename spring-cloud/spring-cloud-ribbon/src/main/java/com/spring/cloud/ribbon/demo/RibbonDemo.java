package com.spring.cloud.ribbon.demo;

import com.google.common.collect.Lists;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * @author xuweizhi
 */
public class RibbonDemo {

    public static void main(String[] args) {

        // 服务列表
        List<Server> serverList = Lists.newArrayList(new Server("localhost", 8080), new Server("localhost", 8082));
        // 构建负载实例
        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
        // 调用 5 次来测试效果
        for (int i = 0; i < 5; i++) {
            // http://localhost:8080/order/findOrderByUserId/1
            // http://localhost:8022/order/findOrderByUserId/1
            String result = LoadBalancerCommand.<String>builder()
                    .withLoadBalancer(loadBalancer).build()
                    .submit(server -> {
                        String address = "http://" + server.getHost() + ":" + server.getPort() + "/order/findOrderByUserId/1";
                        System.out.println(" 调用地址：" + address);
                        URL url = null;
                        try {
                            url = new URL(address);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.connect();
                            InputStream in = conn.getInputStream();
                            byte[] data = new byte[in.available()];
                            in.read(data);
                            return Observable.just(new String(data));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).toBlocking().first();

            System.out.println(" 调用结果：" + result);
        }
    }

}

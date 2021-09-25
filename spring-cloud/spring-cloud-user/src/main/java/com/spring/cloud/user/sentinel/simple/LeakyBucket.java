package com.spring.cloud.user.sentinel.simple;

/**
 * 漏桶限流算法
 */
@SuppressWarnings("all")
public class LeakyBucket {
        public long timeStamp = System.currentTimeMillis();  // 当前时间
        public long capacity; // 桶的容量
        public long rate; // 水漏出的速度(每秒系统能处理的请求数)
        public long water; // 当前水量(当前累积请求数)

        public boolean limit() {
            long now = System.currentTimeMillis();
            water = Math.max(0, water - ((now - timeStamp)/1000) * rate); // 先执行漏水，计算剩余水量
            // timeStamp = now;
            if ((water + 1) < capacity) {
                // 尝试加水,并且水还未满
                water += 1;
                // 加水成功重新赋值
                timeStamp = now;
                return true;
            } else {
                // 水满，拒绝加水
                return false;
        }
    }
}
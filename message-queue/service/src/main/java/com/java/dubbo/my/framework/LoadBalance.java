package com.java.dubbo.my.framework;

import java.util.List;
import java.util.Random;


/**
 * 负载均衡
 *
 * @author xuweizhi
 * @since 2021/05/22
 */
public class LoadBalance {

    public static URL random(List<URL> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }


}

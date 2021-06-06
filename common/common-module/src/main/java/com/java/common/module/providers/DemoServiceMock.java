package com.java.common.module.providers;

/**
 * @author xuweizhi
 * @since 2021/06/01 22:25
 */
public class DemoServiceMock implements DemoService {

    @Override
    public String sayHello(String name) {
        return "出现Rpc异常，进行了mock";
    }
}

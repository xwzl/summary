package com.java.tomcat.io.aio;

import java.io.IOException;

/**
*
* @author xuweizhi
* @since 2020-04-10 22:01
**/
public class BootStrap {
    public  static void main(String[] args) throws IOException {
        new Thread(new AioServer(8080)).start();
    }
}

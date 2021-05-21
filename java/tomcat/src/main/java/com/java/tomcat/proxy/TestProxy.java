package com.java.tomcat.proxy;
/**
*
* @author xuweizhi
* @since 2020-04-20 21:31
**/
public class TestProxy {
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacadeImpl bookProxy = (BookFacadeImpl) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
    }
}

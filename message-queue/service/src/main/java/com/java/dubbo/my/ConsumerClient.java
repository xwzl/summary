package com.java.dubbo.my;

import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import com.java.dubbo.my.api.BookService;
import com.java.dubbo.my.framework.protocol.http.HttpClient;
import com.java.dubbo.my.model.BookVO;
import com.java.dubbo.my.utils.proxy.ServiceProxyFactory;
import com.java.tool.model.vo.ResultVO;

/**
 * consumer client
 *
 * @author xuweizhi
 * @since 2021/05/25 16:00
 */
@SuppressWarnings("all")
public class ConsumerClient {

    public static void main(String[] args) {
        ConsumerClient consumerClient = new ConsumerClient();
        // 1. 固定写死的 rpc 调用
        //consumerClient.baseRemoteProcedure();
        BookService bookService = ServiceProxyFactory.getProxy(BookService.class);
        String result = bookService.helloService("bady");
        System.out.println("返回结果：" + result);
        ResultVO<BookVO> book = bookService.getBookById("1");
        System.out.println(book);
    }

    /**
     * 最基本的远程 rpc 调用
     */
    public void baseRemoteProcedure() {
        HttpClient httpClient = new HttpClient();
        String hostname = "localhost";
        Integer port = 8080;
        Invocation invocation = new Invocation(BookService.class.getName(), "helloService", new Class[]{String.class}, new Object[]{"服务测试"});
        String result = httpClient.send(hostname, port, invocation);
        System.out.println("返回结果：" + result);
    }
}

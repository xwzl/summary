package com.java.dubbo.my.utils.proxy;

import com.alibaba.fastjson.JSON;
import com.java.dubbo.my.api.BookService;
import com.java.dubbo.my.framework.LoadBalance;
import com.java.dubbo.my.framework.URL;
import com.java.dubbo.my.framework.ZookeeperRegister;
import com.java.dubbo.my.framework.config.ApplicationConfig;
import com.java.dubbo.my.framework.config.ZookeeperConfig;
import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import com.java.dubbo.my.framework.protocol.http.HttpClient;
import com.java.dubbo.my.framework.register.RemoteMapRegister;
import com.java.dubbo.my.utils.ApplicationFactory;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理工厂生产对应的 Service Proxy 对象
 *
 * @author xuweizhi
 * @since 2021/05/25 16:12
 */
@SuppressWarnings("all")
public class ServiceProxyFactory {

    /**
     * 得到代理对象
     *
     * @param sourceProxyClass 服务接口
     * @param <T>              代理对象
     * @return 代理对象
     */
    public static <T> T getProxy(final Class<?> sourceProxyClass) {
        return (T) Proxy.newProxyInstance(sourceProxyClass.getClassLoader(), new Class[]{sourceProxyClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // jvm 属性值 -Dmock=return:134
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }
                // 注册


                // 普通调用
                //return baseProxyModel();
                // 通用方法代理
                //String result = customerProxyModel(method, args, sourceProxyClass);
                // 负载均衡调用
                String result = loadBlanceProxyModel(method, args, sourceProxyClass);
                return dealResult(method, result);
            }
        });
    }

    private static Object dealResult(Method method, String result) {
        if (method.getReturnType().getName().equals("void") || result == null) {
            return result;
        }
        return JSON.parseObject(result, method.getReturnType());
    }

    /**
     * 负载均衡方式调用可用服务列表
     *
     * @param method           方法
     * @param args             参数
     * @param sourceProxyClass 代理类
     * @return 代理对象
     */
    private static String loadBlanceProxyModel(Method method, Object[] args, Class<?> sourceProxyClass) {
        Invocation invocation = new Invocation(sourceProxyClass.getName(), method.getName(), method.getParameterTypes(), args);
        List<URL> urlList = getRemoteUrl(sourceProxyClass);
        URL url = LoadBalance.random(urlList);
        //String result = new HttpClient().send(url.getHostname(), url.getPort(), invocation);
        return ProtocolProxyFactory.getProtocol().send(url, invocation);
    }

    private static List<URL> getRemoteUrl(Class<?> sourceProxyClass) {
        // 本地获取服务地址
        List<URL> urls = RemoteMapRegister.get(sourceProxyClass.getName());
        ApplicationFactory applicationFactory = ApplicationConfig.getApplicationFactory();
        ZookeeperConfig zookeeperConfig = applicationFactory.getBean(ZookeeperConfig.class);
        // zookeeper 注册服务替换本地服务
        if (zookeeperConfig.getEnable()) {
            urls = ZookeeperRegister.get(sourceProxyClass.getName());
        }
        return urls;
    }

    /**
     * 通用代理模板,可以调用任意代理类的任意方法
     *
     * @param method           方法
     * @param args             入参
     * @param sourceProxyClass 代理对象
     * @return 请求结果值
     */
    private static String customerProxyModel(Method method, Object[] args, Class<?> sourceProxyClass) {
        Invocation invocation = new Invocation(sourceProxyClass.getName(), method.getName(), method.getParameterTypes(), args);
        return new HttpClient().send("localhost", 8080, invocation);
    }

    /**
     * 基本代理方式，只能处理单一方法
     *
     * @return 请求结果
     */
    private static String baseProxyModel() {
        Invocation invocation = new Invocation(BookService.class.getName(), "helloService", new Class[]{String.class}, new Object[]{"服务测试"});
        return new HttpClient().send("localhost", 8080, invocation);
    }

    @Data
    @ToString
    public static class ReflectTest {

        private String address;

        public void returnClass() {

        }

        @Test
        public void returnClassTest() throws NoSuchMethodException {
            Class<ReflectTest> reflectTestClass = ReflectTest.class;
            Method returnClass = reflectTestClass.getMethod("returnClass");
            System.out.println(returnClass.getReturnType().getName());
        }

        @Test
        public void jsonTest() {
            ReflectTest reflectTest = new ReflectTest();
            reflectTest.setAddress("北京市");

            String objectString = JSON.toJSONString(reflectTest);
            System.out.println(objectString);
            System.out.println(JSON.parseObject(objectString, ReflectTest.class));
            String jsonString = JSON.toJSONString("StringTest");
            System.out.println(jsonString);
            System.out.println(JSON.parseObject(jsonString, String.class));

            List<Object> list = new ArrayList<>();
            list.add("测试");
            String arryString = JSON.toJSONString(list);
            System.out.println(arryString);
            System.out.println(JSON.parseObject(arryString, ArrayList.class));

        }

    }
}

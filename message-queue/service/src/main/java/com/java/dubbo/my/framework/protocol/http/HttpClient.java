package com.java.dubbo.my.framework.protocol.http;

import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http client 发送工具
 *
 *
 * @author xuweizhi
 * @since 2021/05/25 15:40
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {
        try {
            // JDK11之前用
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            OutputStream outputStream = http.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();
            InputStream inputStream = http.getInputStream();
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

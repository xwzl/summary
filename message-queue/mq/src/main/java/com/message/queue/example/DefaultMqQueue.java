package com.message.queue.example;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuweizhi
 * @since 2020/12/02 18:35
 */
public class DefaultMqQueue extends Thread implements MqQueue {

    protected Connection connection;
    public DefaultMqQueue(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void consumer() {
        throw new RuntimeException("子类必须实现该方法");
    }

    @Override
    public void product() throws IOException, TimeoutException, InterruptedException {
        throw new RuntimeException("子类必须实现该方法");
    }

    @Override
    public void run() {
        consumer();
        try {
            product();
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        connection.close();
    }

    protected void startUp() {
        this.start();
        try {
            Thread.sleep(5000);
            this.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.message.queue.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuweizhi
 * @since 2020/12/02 18:34
 */
public interface MqQueue {

    void consumer();

    void product() throws IOException, TimeoutException, InterruptedException;
}

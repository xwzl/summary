package com.turing.java.jvm.concurrent.event;


import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class LongEventProducer {

    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            //2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            //3.获取事件队列传递的数据
            longEvent.setValue(data);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            log.info("生产者准备发送数据...");
            //4.发布事件
            ringBuffer.publish(sequence);
        }
    }
}

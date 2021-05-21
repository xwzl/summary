package com.turing.java.jvm.concurrent.event;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {
    private long serial = 0;

    public LongEventHandler(long serial){
        this.serial = serial;
    }

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("消费者-{}:{}",this.serial,event.getValue());
    }

}

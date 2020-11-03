package com.turing.java.jvm.concurrent.pool.schedule;

import lombok.Data;


@Data
public class HeartBeat {
    private String ip;
    private int port;
    private String appName;
    private String instanceId;
}

package com.java.interview.java.report;

import com.java.interview.java.report.domain.CommonParam;
import com.java.interview.java.report.domain.Context;
import com.java.interview.java.report.domain.ContextHolder;
import com.java.interview.java.report.domain.FieldMapping;
import com.java.interview.java.report.handler.Handler;
import com.java.interview.java.report.handler.HealthcareRecord;
import com.java.interview.java.report.monitor.LogMonitor;
import com.java.interview.java.report.resolve.HealthResolve;
import com.java.interview.java.report.resolve.Resolve;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.java.interview.java.report.enums.HandlerEnum.HEALTHCARE_RECORD_ENUM;

/**
 * 模型工厂
 *
 * @author xuweizhi
 * @since 2022/05/30 14:41
 */
@Slf4j
public class ReportFactory {

    public static void main(String[] args) {



        // 1. 初始化 context holder
        ContextHolder.setContextHolder(Context.builder().dataSource(getStringObjectMap()).handlers(buildResolve()).build());

        // 2. 开启监控
        LogMonitor logHandler = new LogMonitor();
        logHandler.start();


        // 3. 构建执行 handler
        HealthcareRecord exampleHandler = new HealthcareRecord();
        List<Handler> handlers = Collections.singletonList(exampleHandler);

        // 3.1 处理器链 handler 查询数据
        for (Handler handler : handlers) {
            handler.prepare();
        }
        // 3.2 主数据获取
        List<Map<String, Object>> dataSource = (List<Map<String, Object>>) ContextHolder.getDataSource(HEALTHCARE_RECORD_ENUM.key);
        // 3.3 处理链转换数据
        List<Map<String, Object>> healthId = dataSource.stream().map(x -> {
            Map<String, Object> target = new HashMap<>(2);
            handlers.forEach(handler1 -> handler1.transfer(target));
            return target;
        }).collect(Collectors.toList());

        // 4.4 监控统计数据
        logHandler.statics(healthId);

        ContextHolder.remove();
        log.info("{}", healthId);
    }

    private static Map<String, Resolve> buildResolve() {
        Map<String, Resolve> handlers = new HashMap<>(2);
        handlers.put(HEALTHCARE_RECORD_ENUM.getKey(), buildHealthResolve());
        return handlers;
    }

    private static HealthResolve buildHealthResolve() {
        HealthResolve value = new HealthResolve();
        FieldMapping f = new FieldMapping();
        f.setSource("healthId1");
        f.setTarget("test");
        value.setFields(Collections.singletonList(f));
        return value;
    }

    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> dataSource = new HashMap<>();
        CommonParam commonParam = new CommonParam();
        commonParam.setParam1("11");
        commonParam.setParam2("22");
        dataSource.put(HEALTHCARE_RECORD_ENUM.getParam(), commonParam);
        return dataSource;
    }


}

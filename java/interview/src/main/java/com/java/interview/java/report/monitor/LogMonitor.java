package com.java.interview.java.report.monitor;

import com.java.interview.java.report.Monitor;
import com.java.interview.java.report.domain.ContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.java.interview.java.report.enums.HandlerEnum.COMMON_PARAM;

/**
 * @author xuweizhi
 * @since 2022/05/30 18:08
 */
@Slf4j
public class LogMonitor implements Monitor {


    @Override
    public void start() {
        Object dataSource = ContextHolder.getDataSource(COMMON_PARAM.getKey());
        log.info("数据上报入参打印:{}", dataSource);
    }

    @Override
    public void statics(List<Map<String, Object>> list) {
        // 空索引下表 统计
        int emptyIndex = 0;

        for (Map<String, Object> stringObjectMap : list) {
            for (Object value : stringObjectMap.values()) {
                if (value == null) {
                    emptyIndex++;
                    continue;
                }
            }
        }
        // todo 异步数据上传
        log.info("异步数据上传");
        log.info("数据上报统计:累计数据 {},无效数据 {},数据为:{}", list.size(), emptyIndex,list);
    }


}

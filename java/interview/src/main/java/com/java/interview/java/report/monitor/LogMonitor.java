package com.java.interview.java.report.monitor;

import com.java.interview.java.report.Monitor;
import com.java.interview.java.report.domain.CommonParam;
import com.java.interview.java.report.domain.ContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.java.interview.java.report.enums.HandlerEnum.HEALTHCARE_RECORD_ENUM;

/**
 * @author xuweizhi
 * @since 2022/05/30 18:08
 */
@Slf4j
public class LogMonitor implements Monitor {


    @Override
    public void start() {
        CommonParam param = (CommonParam) ContextHolder.getDataSource(HEALTHCARE_RECORD_ENUM.getParam());
        log.info("数据上报入参打印:{}", param);
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
        log.info("数据上报统计:累计数据 {},无效数据 {}", list.size(), emptyIndex);
    }


}

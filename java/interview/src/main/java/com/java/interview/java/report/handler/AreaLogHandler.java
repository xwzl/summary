package com.java.interview.java.report.handler;

import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 18:47
 */
public class AreaLogHandler implements Handler {
    @Override
    public boolean prepare() {
        return true;
    }

    @Override
    public boolean transfer(Map<String, Object> target) {
        return true;
    }


}

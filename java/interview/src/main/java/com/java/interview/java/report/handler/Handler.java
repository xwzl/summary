package com.java.interview.java.report.handler;

import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 15:08
 */
public interface Handler {
    boolean prepare();

    boolean transfer(Map<String, Object> target);
}

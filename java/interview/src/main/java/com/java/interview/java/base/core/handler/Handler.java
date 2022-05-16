package com.java.interview.java.base.core.handler;

import com.java.interview.java.base.core.Context;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:18
 */
public interface Handler<T> {
    T handle(Context context);
}

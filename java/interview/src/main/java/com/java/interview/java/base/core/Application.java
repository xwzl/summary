package com.java.interview.java.base.core;

import com.java.interview.java.base.core.handler.Handler;
import com.java.interview.java.base.core.param.Param;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:18
 */
public interface Application<T> extends Context, Handler<T> {

    T execute(Param param);

}

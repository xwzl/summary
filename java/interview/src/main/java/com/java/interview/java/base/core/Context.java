package com.java.interview.java.base.core;

import com.java.interview.java.base.core.param.Param;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:17
 */
public interface Context {

    Context createContext(Param param);

}

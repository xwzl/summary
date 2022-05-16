package com.java.interview.java.base.core.application;

import com.java.interview.java.base.core.extension.Extension;
import com.java.interview.java.base.core.Application;
import com.java.interview.java.base.core.Context;
import com.java.interview.java.base.core.param.Param;
import lombok.Data;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:19
 */
@Data
public class DefaultApplication<T> implements Application<T> {
    // ps 某些公用字段
    private Extension extension;

    @Override
    public Context createContext(Param param) {
        DefaultApplication<T> context = new DefaultApplication<T>();
        // todo init context content
        context.setExtension(extension);
        System.out.println("default 创建上下文");
        return context;
    }

    @Override
    public T handle(Context context) {
        // 各自的实现类型,对用的context 类型
        DefaultApplication<T> defaultApplication = (DefaultApplication<T>) context;
        Extension extension = defaultApplication.getExtension();
        // todo execute actually business operate
        System.out.println("default execute");
        return (T) "默认实现";
    }

    /**
     * execute 这一步实际被 bdp 包含
     *
     * @param param 入参
     * @return 返回值
     */
    @Override
    public T execute(Param param) {
        // 1. 创建
        Context context = createContext(param);
        // 2. 执行
        return handle(context);
    }
}

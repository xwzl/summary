package leetcode.editor.cn.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 方法执行时间统计
 *
 * @author xuweizhi
 * @since 2020/08/06 15:07
 */
@Slf4j
public class ExecutionTime {

    public static <T> long executionTime(Supplier<T> supplier) {
        return doExecutionTime(supplier);
    }

    private static <T> long doExecutionTime(Supplier<T> supplier) {
        long l = System.currentTimeMillis();
        supplier.get();
        return System.currentTimeMillis() - l;
    }
}

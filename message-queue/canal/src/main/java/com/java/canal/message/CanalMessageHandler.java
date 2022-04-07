package com.java.canal.message;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2022/04/07 20:52
 */
public interface CanalMessageHandler<T> {

    /**
     * 插入数据
     *
     * @param data 数据
     */
    void insert(List<T> data);

    /**
     * 更新数据
     *
     * @param data 数据
     */
    void update(List<T> data);

    /**
     * 删除数据
     *
     * @param data 数据
     */
    void delete(List<T> data);
}

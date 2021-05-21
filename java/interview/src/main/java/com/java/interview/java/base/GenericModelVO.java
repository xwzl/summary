package com.java.interview.java.base;

import lombok.ToString;

/**
 * 泛型对象
 *
 * @author xuweizhi
 * @since 2021/04/29 11:25
 */
@ToString
public class GenericModelVO<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        GenericModelVO<String> genericModelVO = new GenericModelVO<>();
        genericModelVO.setData("泛型桥接方法擦除类型");
    }
}

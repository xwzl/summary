package com.java.tool.model.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static com.java.tool.model.enums.ApiExceptionEnum.SUCCESS;

/**
 * 返回结果值 vo
 *
 * @author xuweizhi
 * @since 2021/05/25 14:02
 */
@Data
@ToString
@Accessors(chain = true)
public class ResultVO<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回结果
     */
    private T data;

    public ResultVO(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<T>(SUCCESS.getStatus(), SUCCESS.getMessage());
    }


    public static <T> ResultVO<T> success(Integer status, String message, T data) {
        return new ResultVO<T>(status, message, data);
    }


    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(SUCCESS.getStatus(), SUCCESS.getMessage(), data);
    }

}

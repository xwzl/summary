package com.java.tool.model.vo;

import com.java.tool.model.enums.ApiExceptionEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 返回结果值 vo
 *
 * @author xuweizhi
 * @since 2021/05/25 14:02
 */
@Data
@ToString
// @Accessors(chain = true)
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


    public ResultVO(T data) {
        this.status = ApiExceptionEnum.SUCCESS.getStatus();
        this.msg = ApiExceptionEnum.SUCCESS.getMessage();
        this.data = data;
    }

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
        return new ResultVO<>(ApiExceptionEnum.SUCCESS.getStatus(), ApiExceptionEnum.SUCCESS.getMessage());
    }


    public static <T> ResultVO<T> success(Integer status, String message, T data) {
        return new ResultVO<>(status, message, data);
    }


    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(ApiExceptionEnum.SUCCESS.getStatus(), ApiExceptionEnum.SUCCESS.getMessage(), data);
    }

}

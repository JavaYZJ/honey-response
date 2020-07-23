package com.eboy.honey.response.commmon;

import com.eboy.honey.response.core.Response;

/**
 * @author yangzhijie
 * @date 2020/7/23 10:00
 */
public class HoneyResponse<T> {
    /**
     * 是否响应成功
     */
    private Boolean success;
    /**
     * 响应编码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应内容
     */
    private T data;

    /**
     * 无参构造器(构造器私有，外部不可以直接创建)
     */
    private HoneyResponse() {
        this.code = 200;
        this.success = true;
    }

    /**
     * 有参构造器(构造器私有，外部不可以直接创建)
     *
     * @param obj 响应数据
     */
    private HoneyResponse(T obj) {
        this.code = 200;
        this.data = obj;
        this.success = true;
    }

    /**
     * 有参构造器(构造器私有，外部不可以直接创建)
     *
     * @param response 异常枚举
     */
    private HoneyResponse(Response response) {
        this.success = false;
        this.code = response.getCode();
        this.message = response.getMessage();
    }


    /**
     * 通用返回成功（没有返回结果）
     *
     * @return HoneyResponse
     */
    public static HoneyResponse success() {
        return new HoneyResponse();
    }

    /**
     * 返回成功（有返回结果）
     *
     * @param data 响应内容
     * @return HoneyResponse
     */
    public static <T> HoneyResponse<T> success(T data) {
        return new HoneyResponse<T>(data);
    }

    /**
     * 通用返回失败
     *
     * @param response 异常枚举
     * @return HoneyResponse
     */
    public static <T> HoneyResponse<T> failure(Response response) {
        return new HoneyResponse<T>(response);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HoneyResponse{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

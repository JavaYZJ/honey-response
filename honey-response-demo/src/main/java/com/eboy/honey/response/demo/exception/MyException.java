package com.eboy.honey.response.demo.exception;

import com.eboy.honey.response.core.HoneyExceptionAssert;

/**
 * @author yangzhijie
 * @date 2020/7/23 16:53
 */
public enum MyException implements HoneyExceptionAssert {

    /**
     * honey-response exception
     */
    HONEY_RESPONSE_EXCEPTION(500, "honey-response exception.");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    MyException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

package com.eboy.honey.response.constant;


import com.eboy.honey.response.core.HoneyExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangzhijie
 * @date 2020/7/17 10:47
 */
@Getter
@AllArgsConstructor
public enum HoneyArgsExceptionException implements HoneyExceptionAssert {


    /**
     * args valid error
     */
    VALID_ERROR(6001, "args valid error.");


    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}

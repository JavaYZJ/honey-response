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
public enum HoneyServletExceptionException implements HoneyExceptionAssert {
    /**
     * 404 page missing
     */
    PAGE_MISSING(4004, "page missing"),

    WEB_UNKNOWN_ERROR(4005, "Web unknown error.");
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}

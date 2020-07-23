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
public enum HoneyUCExceptionException implements HoneyExceptionAssert {

    /**
     * Token not found
     */
    TOKEN_NOT_FOUND(4001, "Token not found."),

    /**
     * Invalid token
     */
    INVALID_TOKEN(4002, "Invalid token."),

    /**
     * Invalid token not have userId claim
     */
    INVALID_TOKEN_NOT_HAVE_USER_ID_CLAIM(4003, "Invalid token not have userId claim."),
    /**
     * user not found
     */
    USER_NOT_FOUND(5001, "user not found.");


    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}

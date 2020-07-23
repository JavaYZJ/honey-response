package com.eboy.honey.response.constant;


import com.eboy.honey.response.core.HoneyExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangzhijie
 * @date 2020/7/17 11:02
 */
@Getter
@AllArgsConstructor
public enum HoneyCommonExceptionException implements HoneyExceptionAssert {


    /**
     * server have error
     */
    SERVER_ERROR(5001, "server have error."),



    /**
     *
     */
    SERVER_I18N_ERROR(5002, "you enable the i18n in your server but not provide a local instance.");
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}

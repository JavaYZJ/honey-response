package com.eboy.honey.response.exception;


import com.eboy.honey.response.core.Response;

/**
 * @author yangzhijie
 * @date 2020/7/14 17:28
 */
public class HoneyBusinessException extends HoneyBaseException {

    private static final long serialVersionUID = 1L;

    public HoneyBusinessException(Response responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public HoneyBusinessException(Response responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, cause, args, message);
    }
}

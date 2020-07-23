package com.eboy.honey.response.exception;


import com.eboy.honey.response.core.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangzhijie
 * @date 2020/7/14 17:29
 */
@Getter
@Setter
public class HoneyBaseException extends RuntimeException {

    private static final long serialVersionUID = -4449068692313111887L;

    String message;

    Throwable cause;

    private Response responseEnum;

    private Object[] args;


    public HoneyBaseException(Response responseEnum) {
        this.responseEnum = responseEnum;
    }

    public HoneyBaseException(Response responseEnum, Object[] args, String message) {
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }

    public HoneyBaseException(Response responseEnum, Throwable cause, Object[] args, String message) {
        this.responseEnum = responseEnum;
        this.cause = cause;
        this.args = args;
        this.message = message;
    }
}

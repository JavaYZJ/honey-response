package com.eboy.honey.response.demo.config.handler;

import com.eboy.honey.response.commmon.HoneyErrorResponse;
import com.eboy.honey.response.core.HoneyExceptionHandler;
import com.eboy.honey.response.exception.HoneyBaseException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author yangzhijie
 * @date 2020/7/23 17:40
 */
//@ControllerAdvice
//@ConditionalOnWebApplication
public class MyHoneyResponseExceptionHandler implements HoneyExceptionHandler {
    @Override
    public HoneyErrorResponse handleBusinessException(HoneyBaseException e) {
        return null;
    }
    @Override
    public HoneyErrorResponse handleBaseException(HoneyBaseException e) {
        return null;
    }
    @Override
    public HoneyErrorResponse handleServletException(Exception e) {
        return null;
    }
    @Override
    public HoneyErrorResponse handleBindException(BindException e) {
        return null;
    }
    @Override
    public HoneyErrorResponse handleValidException(MethodArgumentNotValidException e) {
        return null;
    }

    @Override
    public HoneyErrorResponse handleException(Exception e) {
        return null;
    }
}

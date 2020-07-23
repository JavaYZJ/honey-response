package com.eboy.honey.response.core;

import com.eboy.honey.response.commmon.HoneyErrorResponse;
import com.eboy.honey.response.exception.HoneyBaseException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author yangzhijie
 * @date 2020/7/21 11:30
 */
public interface HoneyExceptionHandler {



    /**
     * 业务异常处理
     *
     * @param e HoneyBaseException
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleBusinessException(HoneyBaseException e);

    /**
     * 自定义异常处理
     *
     * @param e HoneyBaseException
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleBaseException(HoneyBaseException e);

    /**
     * servlet异常处理
     *
     * @param e Exception
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleServletException(Exception e);

    /**
     * 参数绑定异常
     *
     * @param e BindException
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleBindException(BindException e);

    /**
     * 参数校验异常
     *
     * @param e MethodArgumentNotValidException
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleValidException(MethodArgumentNotValidException e);

    /**
     * 未定义异常
     *
     * @param e Exception
     * @return HoneyErrorResponse
     */
    HoneyErrorResponse handleException(Exception e);

}

package com.eboy.honey.response.handler;

import com.ctrip.framework.foundation.internals.provider.DefaultServerProvider;
import com.eboy.honey.response.autoconfigure.config.HoneyEnvConfigProperties;
import com.eboy.honey.response.commmon.HoneyErrorResponse;
import com.eboy.honey.response.constant.HoneyArgsExceptionException;
import com.eboy.honey.response.constant.HoneyCommonExceptionException;
import com.eboy.honey.response.constant.HoneyServletExceptionException;
import com.eboy.honey.response.core.HoneyEnvConfig;
import com.eboy.honey.response.core.HoneyExceptionHandler;
import com.eboy.honey.response.core.HoneyMessage;
import com.eboy.honey.response.exception.HoneyBaseException;
import com.eboy.honey.response.exception.HoneyBusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @author yangzhijie
 * @date 2020/7/17 9:55
 */
@Slf4j
@ControllerAdvice
@ConditionalOnWebApplication
@Data
public class HoneyExceptionDefaultHandler implements HoneyExceptionHandler {

    /**
     * 自定义消息
     */
    @Autowired
    private HoneyMessage honeyMessage;

    /**
     * 环境配置
     */
    @Autowired
    private HoneyEnvConfig honeyEnvConfig;

    /**
     * 获取当前环境配置属性
     *
     * @return 当前环境配置属性
     */
    private HoneyEnvConfigProperties getHoneyEnvConfig() {
        return honeyEnvConfig.setHoneyEnvConfig();
    }

    /**
     * 获取当前环境
     *
     * @return 当前环境
     */
    private String getSystemEnv() {
        if (getHoneyEnvConfig().isEnableApollo()) {
            DefaultServerProvider provider = new DefaultServerProvider();
            provider.initialize();
            return provider.getEnvType();
        }
        return getHoneyEnvConfig().getEnv();
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */

    @Override
    @ExceptionHandler(value = HoneyBusinessException.class)
    @ResponseBody
    public HoneyErrorResponse handleBusinessException(HoneyBaseException e) {
        log.warn(e.getMessage(), e);
        return new HoneyErrorResponse(e.getResponseEnum().getCode(), honeyMessage.getMessage(e));
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @Override
    @ExceptionHandler(value = HoneyBaseException.class)
    @ResponseBody
    public HoneyErrorResponse handleBaseException(HoneyBaseException e) {
        log.warn(e.getMessage(), e);
        return new HoneyErrorResponse(e.getResponseEnum().getCode(), honeyMessage.getMessage(e));
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @Override
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public HoneyErrorResponse handleServletException(Exception e) {
        log.warn(e.getMessage(), e);
        int code = HoneyServletExceptionException.WEB_UNKNOWN_ERROR.getCode();
        try {
            HoneyServletExceptionException servletExceptionEnum = HoneyServletExceptionException.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), HoneyServletExceptionException.class.getName());
        }
        if (getHoneyEnvConfig().getEnvProd().equalsIgnoreCase(getSystemEnv())) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            code = HoneyCommonExceptionException.SERVER_ERROR.getCode();
            HoneyBaseException honeyBaseException = new HoneyBaseException(HoneyServletExceptionException.PAGE_MISSING);
            String message = honeyMessage.getMessage(honeyBaseException);
            return new HoneyErrorResponse(code, message);
        }
        return new HoneyErrorResponse(code, e.getMessage());
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @Override
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public HoneyErrorResponse handleBindException(BindException e) {
        log.warn("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @Override
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public HoneyErrorResponse handleValidException(MethodArgumentNotValidException e) {
        log.warn("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private HoneyErrorResponse wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

        }
        return new HoneyErrorResponse(HoneyArgsExceptionException.VALID_ERROR.getCode(), msg.substring(2));
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @Override
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HoneyErrorResponse handleException(Exception e) {
        log.warn(e.getMessage(), e);
        if (getHoneyEnvConfig().getEnvProd().equalsIgnoreCase(getSystemEnv())) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            int code = HoneyCommonExceptionException.SERVER_ERROR.getCode();
            HoneyBaseException honeyBaseException = new HoneyBaseException(HoneyCommonExceptionException.SERVER_ERROR);
            String message = honeyMessage.getMessage(honeyBaseException);
            return new HoneyErrorResponse(code, message);
        }

        return new HoneyErrorResponse(HoneyCommonExceptionException.SERVER_ERROR.getCode(), e.getMessage());
    }
}

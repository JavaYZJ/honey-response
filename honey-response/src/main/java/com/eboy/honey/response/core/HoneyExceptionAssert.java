package com.eboy.honey.response.core;



import com.eboy.honey.response.exception.HoneyBaseException;
import com.eboy.honey.response.exception.HoneyBusinessException;

import java.text.MessageFormat;

/**
 * @author yangzhijie
 * @date 2020/7/14 17:31
 */
public interface HoneyExceptionAssert extends Response, HoneyAssert {

    /**
     * 重写自定义Assert 接口的方法
     *
     * @param args 参数
     * @return BaseException
     */
    @Override
    default HoneyBaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new HoneyBusinessException(this, args, msg);
    }

    /**
     * 重写自定义Assert接口的方法
     *
     * @param t    Throwable
     * @param args 任意参数
     * @return BaseException
     */
    @Override
    default HoneyBaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new HoneyBusinessException(this, args, msg, t);
    }

}

package com.eboy.honey.response.core;

import com.eboy.honey.response.exception.HoneyBaseException;

/**
 * @author yangzhijie
 * @date 2020/7/21 13:21
 */
public interface HoneyMessage {

    /**
     * 定义消息
     *
     * @param e HoneyBaseException
     * @return String
     */
    String getMessage(HoneyBaseException e);
}

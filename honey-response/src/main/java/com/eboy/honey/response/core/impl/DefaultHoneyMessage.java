package com.eboy.honey.response.core.impl;


import com.eboy.honey.response.core.HoneyMessage;
import com.eboy.honey.response.exception.HoneyBaseException;

/**
 * @author yangzhijie
 * @date 2020/7/17 10:10
 */
public class DefaultHoneyMessage implements HoneyMessage {

    @Override
    public String getMessage(HoneyBaseException e) {
        return e.getMessage();
    }

}

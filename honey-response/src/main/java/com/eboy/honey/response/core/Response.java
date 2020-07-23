package com.eboy.honey.response.core;

/**
 * @author yangzhijie
 * @date 2020/7/14 17:24
 */
public interface Response {

    /**
     * 获取code值
     *
     * @return code
     */
    int getCode();

    /**
     * 获取具体信息
     *
     * @return message
     */
    String getMessage();
}

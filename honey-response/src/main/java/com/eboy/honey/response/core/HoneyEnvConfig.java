package com.eboy.honey.response.core;


import com.eboy.honey.response.autoconfigure.config.HoneyEnvConfigProperties;

/**
 * @author yangzhijie
 * @date 2020/7/22 14:15
 */
public interface HoneyEnvConfig {

    /**
     * 设置环境配置
     *
     * @return 环境配置
     */
    HoneyEnvConfigProperties setHoneyEnvConfig();


    /**
     * 获取当前环境
     *
     * @return 当前环境
     */
    String getSystemEnv();
}

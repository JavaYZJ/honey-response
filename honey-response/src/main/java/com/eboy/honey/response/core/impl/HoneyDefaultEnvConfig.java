package com.eboy.honey.response.core.impl;

import com.ctrip.framework.foundation.internals.provider.DefaultServerProvider;
import com.eboy.honey.response.autoconfigure.config.HoneyEnvConfigProperties;
import com.eboy.honey.response.core.HoneyEnvConfig;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author yangzhijie
 * @date 2020/7/22 14:00
 */
public class HoneyDefaultEnvConfig implements HoneyEnvConfig {
    /**
     * 当前环境 默认dev
     */
    @Value("${spring.profiles.active:dev}")
    private String env;

    @Override
    public HoneyEnvConfigProperties setHoneyEnvConfig() {
        HoneyEnvConfigProperties honeyEnvConfigProperties = new HoneyEnvConfigProperties();
        // 不开启apollo
        honeyEnvConfigProperties.setEnableApollo(false);
        // 设置生产环境
        String envProd = "prod";
        honeyEnvConfigProperties.setEnvProd(envProd);
        // 设置当前环境
        honeyEnvConfigProperties.setEnv(env);
        return honeyEnvConfigProperties;
    }

    @Override
    public String getSystemEnv() {
        if (setHoneyEnvConfig().isEnableApollo()) {
            DefaultServerProvider provider = new DefaultServerProvider();
            provider.initialize();
            String envType = provider.getEnvType();
            if (envType == null) {
                throw new IllegalStateException("获取apollo当前环境失败");
            }
            return envType;
        }
        return setHoneyEnvConfig().getEnv();
    }
}

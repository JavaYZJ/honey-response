package com.eboy.honey.response.demo.config.honeyenv;

import com.eboy.honey.response.autoconfigure.config.HoneyEnvConfigProperties;
import com.eboy.honey.response.core.HoneyEnvConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yangzhijie
 * @date 2020/7/23 17:31
 */
//@Component
public class MyHoneyEncConfig implements HoneyEnvConfig{

    @Override
    public HoneyEnvConfigProperties setHoneyEnvConfig() {
        return null;
    }

    @Override
    public String getSystemEnv() {
        return null;
    }
}

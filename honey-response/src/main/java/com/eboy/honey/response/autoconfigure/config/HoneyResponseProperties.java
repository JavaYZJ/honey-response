package com.eboy.honey.response.autoconfigure.config;

/**
 * @author yangzhijie
 * @date 2020/7/21 17:00
 */
//@ConfigurationProperties(prefix = "spring.honey.response")
public class HoneyResponseProperties {

    private boolean enableI18n;

    public boolean isEnableI18n() {
        return enableI18n;
    }

    public void setEnableI18n(boolean enableI18n) {
        this.enableI18n = enableI18n;
    }
}

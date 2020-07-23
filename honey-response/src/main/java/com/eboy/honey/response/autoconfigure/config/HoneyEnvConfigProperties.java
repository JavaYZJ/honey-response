package com.eboy.honey.response.autoconfigure.config;

/**
 * @author yangzhijie
 * @date 2020/7/22 14:18
 */
public class HoneyEnvConfigProperties {
    /**
     * 生产环境
     */
    private String envProd;

    /**
     * 是否开启了apollo
     */
    private boolean enableApollo;
    /**
     * 当前环境
     */
    private String env;

    public String getEnvProd() {
        return envProd;
    }

    public void setEnvProd(String envProd) {
        this.envProd = envProd;
    }

    public boolean isEnableApollo() {
        return enableApollo;
    }

    public void setEnableApollo(boolean enableApollo) {
        this.enableApollo = enableApollo;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}

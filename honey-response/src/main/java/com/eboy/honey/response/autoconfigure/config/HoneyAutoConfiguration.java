package com.eboy.honey.response.autoconfigure.config;


import com.eboy.honey.response.autoconfigure.annotaion.HoneyConditionalOnProperty;
import com.eboy.honey.response.constant.HoneyArgsExceptionException;
import com.eboy.honey.response.constant.HoneyCommonExceptionException;
import com.eboy.honey.response.constant.HoneyServletExceptionException;
import com.eboy.honey.response.constant.HoneyUCExceptionException;
import com.eboy.honey.response.core.HoneyEnvConfig;
import com.eboy.honey.response.core.HoneyExceptionHandler;
import com.eboy.honey.response.core.HoneyMessage;
import com.eboy.honey.response.core.impl.DefaultHoneyMessage;
import com.eboy.honey.response.core.impl.HoneyDefaultEnvConfig;
import com.eboy.honey.response.handler.HoneyExceptionDefaultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

/**
 * @author yangzhijie
 * @date 2020/7/17 11:31
 */
@Configuration
@ConditionalOnClass({
        HoneyArgsExceptionException.class, HoneyCommonExceptionException.class,
        HoneyServletExceptionException.class, HoneyUCExceptionException.class
})
@Slf4j
public class HoneyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HoneyExceptionHandler.class)
    public HoneyExceptionDefaultHandler honeyExceptionHandler() {
        log.info("honey-response默认的HoneyExceptionDefaultHandler自动装配完成");
        return new HoneyExceptionDefaultHandler();
    }

    @Bean
    @ConditionalOnMissingBean(HoneyEnvConfig.class)
    public HoneyEnvConfig honeyEnvConfig() {
        log.info("honey-response默认的HoneyEnvConfig自动装配完成");
        return new HoneyDefaultEnvConfig();
    }

    @Bean
    @ConditionalOnMissingBean(HoneyMessage.class)
    public DefaultHoneyMessage honeyMessage() {
        log.info("honey-response默认的honeyMessage自动装配完成");
        return new DefaultHoneyMessage();
    }


    @Bean("messageSource")
    @ConditionalOnMissingBean(MessageSource.class)
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setFallbackToSystemLocale(false);
        log.info("honey-response 自动装配 messageSource完成");
        return messageSource;
    }

}

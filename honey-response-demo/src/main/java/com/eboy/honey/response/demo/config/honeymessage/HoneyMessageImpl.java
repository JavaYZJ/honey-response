package com.eboy.honey.response.demo.config.honeymessage;


import com.eboy.honey.response.core.HoneyMessage;
import com.eboy.honey.response.exception.HoneyBaseException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author yangzhijie
 * @date 2020/7/21 13:42
 */
@Component
public class HoneyMessageImpl implements HoneyMessage {

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @Override
    public String getMessage(HoneyBaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        try {
            String message = getMessage(code, e.getArgs(), LocaleContextHolder.getLocale());
            if (message == null || message.isEmpty()) {
                return e.getMessage();
            }
            return message;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

}

package com.eboy.honey.response.autoconfigure.annotaion;


import com.eboy.honey.response.autoconfigure.config.HoneyAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yangzhijie
 * @date 2020/7/17 11:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HoneyAutoConfiguration.class)
public @interface EnableHoneyResponse {
}

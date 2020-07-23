package com.eboy.honey.response.autoconfigure.annotaion;


import com.eboy.honey.response.autoconfigure.condition.HoneyOnPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author yangzhijie
 * @date 2020/7/22 9:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(HoneyOnPropertyCondition.class)
public @interface HoneyConditionalOnProperty {

    String[] value() default {};

    String prefix() default "";

    String[] name() default {};

    String havingValue() default "";

    boolean enableApollo() default false;

    boolean matchIfMissing() default false;
}

package com.eboy.honey.response.core;

import com.eboy.honey.response.exception.HoneyBaseException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author yangzhijie
 * @date 2020/7/14 17:41
 */
public interface HoneyAssert {

    /**
     * 创建异常
     *
     * @param args 参数
     * @return BaseException
     */
    HoneyBaseException newException(Object... args);

    /**
     * 创建异常
     *
     * @param t    Throwable
     * @param args 任意参数
     * @return BaseException
     */
    HoneyBaseException newException(Throwable t, Object... args);


    /**
     * 扔出异常
     */
    default void throwException() {
        throw newException();
    }

    /**
     * 断言对象非空
     *
     * @param obj 待判断对象
     */

    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException(obj);
        }
    }

    /**
     * 断言 为空
     *
     * @param obj 断言对象
     */
    default void assertIsNull(Object obj) {
        if (obj != null) {
            throw newException(obj);
        }
    }

    /**
     * 断言表达式
     *
     * @param expression 断言表达式
     */
    default void assertIsTrue(boolean expression) {
        if (!expression) {
            throw newException(expression);
        }
    }

    /**
     * 断言集合非空
     *
     * @param collection 集合
     */
    default void assertNotEmpty(@Nullable Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw newException(collection);
        }
    }

    /**
     * 断言map非空
     *
     * @param map map
     */
    default void assertNotEmpty(@Nullable Map<?, ?> map) {
        if (CollectionUtils.isEmpty(map)) {
            throw newException(map);
        }
    }
}

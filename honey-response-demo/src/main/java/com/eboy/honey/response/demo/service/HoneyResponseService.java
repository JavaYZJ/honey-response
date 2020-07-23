package com.eboy.honey.response.demo.service;

import com.eboy.honey.response.constant.HoneyCommonExceptionException;
import com.eboy.honey.response.constant.HoneyUCExceptionException;
import org.springframework.stereotype.Service;

/**
 * @author yangzhijie
 * @date 2020/7/23 11:24
 */
@Service
public class HoneyResponseService {

    public String test() {
        HoneyUCExceptionException.INVALID_TOKEN.assertIsTrue(false);
        return "test";
    }
}

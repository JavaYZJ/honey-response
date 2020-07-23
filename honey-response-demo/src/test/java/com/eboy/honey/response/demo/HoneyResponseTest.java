package com.eboy.honey.response.demo;

import com.eboy.honey.response.demo.service.HoneyResponseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangzhijie
 * @date 2020/7/23 11:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HoneyResponseTest {

    @Autowired
    private HoneyResponseService honeyResponseService;

    @Test
    public void test(){
       honeyResponseService.test();
    }
}

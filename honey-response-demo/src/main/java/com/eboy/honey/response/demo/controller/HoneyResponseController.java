package com.eboy.honey.response.demo.controller;

import com.eboy.honey.response.commmon.HoneyResponse;
import com.eboy.honey.response.constant.HoneyUCExceptionException;
import com.eboy.honey.response.demo.annotation.AuthSkip;
import com.eboy.honey.response.demo.entity.Honey;
import com.eboy.honey.response.demo.service.HoneyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangzhijie
 * @date 2020/7/23 11:14
 */
@RestController
@RequestMapping("/honey-response")
public class HoneyResponseController {

    @Autowired
    private HoneyResponseService honeyResponseService;

    @GetMapping("/success")
    public HoneyResponse success() {
        return HoneyResponse.success("hello honey-response");
    }

    @GetMapping("/emptySuccess")
    public HoneyResponse emptySuccess() {
        return HoneyResponse.success();
    }

    @GetMapping("/failure")
    public HoneyResponse failure() {
        return HoneyResponse.failure(HoneyUCExceptionException.INVALID_TOKEN);
    }

    @PostMapping("/test")
    @AuthSkip
    public HoneyResponse test(@Validated @RequestBody Honey honey){
        return HoneyResponse.success(honeyResponseService.test());
    }
}

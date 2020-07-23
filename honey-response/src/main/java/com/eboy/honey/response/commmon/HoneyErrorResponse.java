package com.eboy.honey.response.commmon;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangzhijie
 * @date 2020/7/17 9:57
 */
@Data
@AllArgsConstructor
public class HoneyErrorResponse {

    /**
     * 响应编码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;
}

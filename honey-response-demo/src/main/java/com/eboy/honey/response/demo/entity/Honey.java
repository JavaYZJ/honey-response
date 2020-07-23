package com.eboy.honey.response.demo.entity;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author yangzhijie
 * @date 2020/7/23 13:44
 */
public class Honey {


    @NotBlank
    private String honeyName;
    @NotBlank
    private String honeyId;

    public String getHoneyName() {
        return honeyName;
    }

    public void setHoneyName(String honeyName) {
        this.honeyName = honeyName;
    }

    public String getHoneyId() {
        return honeyId;
    }

    public void setHoneyId(String honeyId) {
        this.honeyId = honeyId;
    }
}

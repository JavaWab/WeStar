package com.westar.wab.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by wanganbang on 11/8/16.
 */
public class ResetPasswordVO {
    @NotNull(message = "phone is not null")
    private String phone;
    @NotNull(message = "newpwd is not null")
    private String newpwd;
    @NotNull(message = "captcha is not null")
    private String captcha;

    @JsonCreator
    public ResetPasswordVO(@JsonProperty("phone") String phone, @JsonProperty("newpwd") String newpwd, @JsonProperty("captcha") String captcha) {
        this.phone = phone;
        this.newpwd = newpwd;
        this.captcha = captcha;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}

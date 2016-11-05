package com.westar.wab.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserVO
 *
 * @author Anbang Wang
 * @date 2016/11/4
 */
public class UserVO {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phone")
    private String phone;

    @JsonCreator
    public UserVO(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("phone") String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

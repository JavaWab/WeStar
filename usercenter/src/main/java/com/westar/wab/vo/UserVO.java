package com.westar.wab.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * UserVO
 *
 * @author Anbang Wang
 * @date 2016/11/4
 */
public class UserVO {
    @NotNull(message = "username is null")
    @JsonProperty("nickname")
    private String nickname;
    @NotNull(message = "password is null")
    @JsonProperty("password")
    private String password;
    @NotNull(message = "phone is null")
    @JsonProperty("phone")
    private String phone;
    @NotNull(message = "icon is null")
    @JsonProperty("icon")
    private String icon;
    @NotNull(message = "gender is null")
    @JsonProperty("gender")
    private String gender;
    @NotNull(message = "birthday is null")
    @JsonProperty("birthday")
    private Date birthday;

    @JsonCreator
    public UserVO(@JsonProperty("nickname") String nickname, @JsonProperty("password") String password, @JsonProperty("phone") String phone, @JsonProperty("icon") String icon, @JsonProperty("gender") String gender, @JsonProperty("birthday") Date birthday) {
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.icon = icon;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

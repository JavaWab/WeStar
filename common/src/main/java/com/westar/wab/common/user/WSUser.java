package com.westar.wab.common.user;

/**
 * Created by ouyasukuni on 2016/11/1.
 */

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "user")
//@CompoundIndexes(
//        {
//                @CompoundIndex(name = "User_index", unique = true, def = "{uid:1,phone:1,order:1,createTime:1}")
//        }
//)
public class WSUser {
    @Indexed(unique = true)
    private String uid;
    @Indexed(unique = true)
    private String username;
    private String password;
    private boolean enabled = true, accountNonExpired = true, credentialsNonExpired = true, accountNonLocked = true;
    private List<String> authorities = new ArrayList<String>();
    @Indexed(unique = true)
    private String phone;
    private String nick;
    private String birthday;
    private double higth;
    private double weight;
    private String contact;
    private String location;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint GEOlocation;
    @Indexed(direction = IndexDirection.DESCENDING)
    private Date createTime;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int order;

    public WSUser(String nick, String password) {
        this.uid = UUID.randomUUID().toString();
        this.nick = nick;
        this.password = password;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getHigth() {
        return higth;
    }

    public void setHigth(double higth) {
        this.higth = higth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GeoJsonPoint getGEOlocation() {
        return GEOlocation;
    }

    public void setGEOlocation(GeoJsonPoint GEOlocation) {
        this.GEOlocation = GEOlocation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

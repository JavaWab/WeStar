package com.westar.wab.controller;

import com.westar.wab.common.response.OKReSponse;
import com.westar.wab.common.user.WSUser;
import com.westar.wab.service.UserService;
import com.westar.wab.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ouyasukuni on 2016/11/1.
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "Hello Test Oauth2 Resource Service";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Map> addUser(@RequestBody @Validated UserVO user){
        WSUser tuser = new WSUser(user.getUsername(), user.getPassword());
        tuser.setPhone(user.getPhone());
        tuser.setPhone();
        tuser.setBirthday();
        tuser.setNick();
        List<String> authorities = new ArrayList<>();
        authorities.add("USER");
        tuser.setAuthorities(authorities);
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(new Point(116.34, 33.23));

        tuser.setGEOlocation(geoJsonPoint);
        userService.addUser(tuser);
        return new OKReSponse();
    }
}

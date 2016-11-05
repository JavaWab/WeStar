package com.westar.wab.userdetails;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * Created by ouyasukuni on 2016/11/1.
 */
public class UserPressDetailsService implements UserDetailsService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BasicDBObject user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(s)), BasicDBObject.class, "user");
        JSONObject userjson = new JSONObject(user);
        JSONArray authorities = userjson.optJSONArray("authorities");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (authorities != null) {
            for (int i = 0; i < authorities.length(); i++) {
                authorityList.add(new SimpleGrantedAuthority(authorities.getString(i)));
            }
        }
        User idsuser = new User(user.getString("username"), user.getString("password"), authorityList);
        return idsuser;
    }
}

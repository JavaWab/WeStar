package com.westar.wab.service;

import com.westar.wab.common.user.WSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ouyasukuni on 2016/11/1.
 */
@Service
public class UserService implements IUserService{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public WSUser addUser(WSUser user) {
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public void updateUser(WSUser user) {

    }

    @Override
    public WSUser getUserByPhone(String phone) {
        return null;
    }
}

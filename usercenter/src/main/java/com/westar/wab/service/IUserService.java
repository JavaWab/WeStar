package com.westar.wab.service;

import com.westar.wab.common.user.WSUser;

/**
 * Created by wanganbang on 11/8/16.
 */
public interface IUserService {
    public WSUser addUser(WSUser user);
    public void updateUser(WSUser user);
    public WSUser getUserByPhone(String phone);
}

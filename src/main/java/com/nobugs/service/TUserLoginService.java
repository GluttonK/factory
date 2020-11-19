package com.nobugs.service;

import com.nobugs.entity.TUser;

public interface TUserLoginService {

    TUser getUser(int id);

    TUser getUser(String uname, String upwd);
}

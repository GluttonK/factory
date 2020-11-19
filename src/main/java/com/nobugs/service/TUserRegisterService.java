package com.nobugs.service;

import com.nobugs.entity.TFactory;
import com.nobugs.entity.TUser;

public interface TUserRegisterService {

    TUser getUser(int id);
    int register(TUser tUser, TFactory tFactory);
}

package com.nobugs.service.impl;

import com.nobugs.dao.TUserMapper;
import com.nobugs.entity.TUser;
import com.nobugs.service.TUserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tUserLoginService")
public class TUserLoginServiceImpl  implements TUserLoginService {
    @Resource
    private TUserMapper tUserMapper;


    @Override
    public TUser getUser(int id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        return tUser;
    }

    @Override
    public TUser getUser(String uname, String upwd) {
        TUser tUser=tUserMapper.selectByUnameAndPwd(uname,upwd);
        return tUser;
    }
}

package com.nobugs.service.impl;

import com.nobugs.dao.TFactoryMapper;
import com.nobugs.dao.TUserMapper;
import com.nobugs.entity.TFactory;
import com.nobugs.entity.TUser;
import com.nobugs.service.TUserRegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("tUserRegisterService")
public class TUserRegisterServiceImpl implements TUserRegisterService {

    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private TFactoryMapper tFactoryMapper;

    private Date date = new Date();
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public TUser getUser(int id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        return tUser;
    }

    @Override
    public int register(TUser tUser, TFactory tFactory) {
        if(tUserMapper.selectByUserName(tUser.getUserName()) != null){
            return 1;
        }
        if(tFactoryMapper.selectByFactoryName(tFactory.getFactoryName()) != null){
//            System.out.println("YYY");
//            System.out.println(tFactory.getFactoryName());
            return 2;
        }
        tUser.setCreateTime(date);
        tFactory.setCreateTime(date);
        if(tFactoryMapper.insertSelective(tFactory) == 0){
            return 3;
        }
        tUser.setFactoryId(tFactory.getId());
        if(tUserMapper.insertSelective(tUser) == 0){
            return 3;
        }
        return 0;
//        tUser.setFactoryId(1);
//        tUserMapper.insertSelective(tUser);
//        return 0;
    }
}

package com.nobugs.dao;

import com.nobugs.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);
    TUser selectByUserName(@Param(value = "userName") String userName);
    TUser selectByUnameAndPwd(@Param("uname")String uname,@Param("upwd") String upwd);


    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}
package com.nobugs.dao;

import com.nobugs.entity.TUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);
}
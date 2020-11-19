package com.nobugs.dao;

import com.nobugs.entity.TRolePermit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TRolePermitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRolePermit record);

    int insertSelective(TRolePermit record);

    TRolePermit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRolePermit record);

    int updateByPrimaryKey(TRolePermit record);
}
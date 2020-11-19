package com.nobugs.dao;

import com.nobugs.entity.TFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TFactoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFactory record);

    int insertSelective(TFactory record);

    TFactory selectByPrimaryKey(Integer id);
    TFactory selectByFactoryName(@Param(value = "Fname") String Fname);

    int updateByPrimaryKeySelective(TFactory record);

    int updateByPrimaryKey(TFactory record);

}
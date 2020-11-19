package com.nobugs.dao;

import com.nobugs.entity.TPermit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TPermitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermit record);

    int insertSelective(TPermit record);

    TPermit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermit record);

    int updateByPrimaryKey(TPermit record);
}
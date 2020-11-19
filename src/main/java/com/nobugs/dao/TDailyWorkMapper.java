package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TDailyWork;
import com.nobugs.entity.TOrderTrack;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

import java.util.ArrayList;


@Mapper
public interface TDailyWorkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDailyWork record);

    int insertSelective(TDailyWork record);

    TDailyWork selectByPrimaryKey(Integer id);


    List<TDailyWork> selectByPlanId(Integer id);

    ArrayList<TDailyWork> searchByPage(Page<TDailyWork> page);

    int updateByPrimaryKeySelective(TDailyWork record);

    int updateByPrimaryKey(TDailyWork record);
}
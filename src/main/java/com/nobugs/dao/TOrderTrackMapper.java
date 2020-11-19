package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TOrderTrack;
import com.nobugs.entity.TProductPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TOrderTrackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrderTrack record);

    int insertSelective(TOrderTrack record);

    ArrayList<TOrderTrack> selectByPage(Page<TOrderTrack> page);

    List<TOrderTrack> selectByPlanId(@Param(value = "planId") Integer planId);

    ArrayList<TOrderTrack> searchByPage(Page<TOrderTrack> page);

    TOrderTrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrderTrack record);

    int updateByPrimaryKey(TOrderTrack record);
}
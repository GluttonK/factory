package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TProductOrder;
import com.nobugs.entity.TProductPlan;
import com.nobugs.entity.TProductSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface TProductScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProductSchedule record);

    int insertSelective(TProductSchedule record);

    TProductSchedule select(String planSeq,String scheduleSeq);

    List<TProductSchedule> getallschedule();

    ArrayList<TProductSchedule> getallByPage(Page<TProductSchedule> page);

    ArrayList<TProductSchedule> searchByPage(Page<TProductSchedule> page);

    TProductSchedule selectByPrimaryKey(Integer id);

    List<TProductSchedule> selectByPlanId(@Param(value = "planId") Integer planId);

    int updateByPrimaryKeySelective(TProductSchedule record);

    int updateByPrimaryKey(TProductSchedule record);

    int updateCom(@Param(value = "planId") Integer planId);

    List<TProductSchedule> selectScheduleSeqs();
}

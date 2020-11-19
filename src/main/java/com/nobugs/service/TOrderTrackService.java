package com.nobugs.service;

import com.nobugs.entity.*;

import java.util.ArrayList;

public interface TOrderTrackService {

    ArrayList<TOrderTrack> getByPage(Page<TOrderTrack> page);

    ArrayList<TDailyWork> getByPage1(Page<TDailyWork> page);

    int deleteordertrack (Integer id);

    int updateOrderTrack(TDailyWork tDailyWork);

    int updateOrderTrackStatus(TProductSchedule tProductSchedule);

    int addOrderTrack(TDailyWork tDailyWork);

//    ArrayList<TProductSchedule> getScheduleSeqs();
}

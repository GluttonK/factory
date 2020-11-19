package com.nobugs.service.impl;

import com.nobugs.dao.TDailyWorkMapper;
import com.nobugs.dao.TOrderTrackMapper;
import com.nobugs.dao.TProductScheduleMapper;
import com.nobugs.entity.*;
import com.nobugs.service.TOrderTrackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("tProductOrderTrackService")
public class TProductOrderTrackServiceImpl implements TOrderTrackService {
    @Resource
    private TOrderTrackMapper tOrderTrackMapper;
    @Resource
    private TDailyWorkMapper tDailyWorkMapper;
    @Resource
    private TProductScheduleMapper tProductScheduleMapper;

    @Override
    public ArrayList<TOrderTrack> getByPage(Page<TOrderTrack> page) {
        return tOrderTrackMapper.searchByPage(page);
    }

    @Override
    public ArrayList<TDailyWork> getByPage1(Page<TDailyWork> page) {
        return tDailyWorkMapper.searchByPage(page);
    }

    @Override
    public int deleteordertrack(Integer id) {
        return tDailyWorkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateOrderTrack(TDailyWork tDailyWork) {
        return tDailyWorkMapper.updateByPrimaryKeySelective(tDailyWork);
    }

    @Override
    public int updateOrderTrackStatus(TProductSchedule tProductSchedule) {
        tProductSchedule.setScheduleStatus(3);
        return tProductScheduleMapper.updateByPrimaryKeySelective(tProductSchedule);
    }

    @Override
    public int addOrderTrack(TDailyWork tDailyWork) {
        return tDailyWorkMapper.insertSelective(tDailyWork);
    }

//    @Override
//    public ArrayList<TProductSchedule> getScheduleSeqs() {
//        return (ArrayList<TProductSchedule>) tProductScheduleMapper.selectScheduleSeqs();
//    }
}

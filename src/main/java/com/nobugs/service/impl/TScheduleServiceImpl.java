package com.nobugs.service.impl;

import com.nobugs.dao.*;
import com.nobugs.entity.*;
import com.nobugs.service.TScheduleService;
import com.nobugs.util.TimeStamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service("scheduleService")
public class TScheduleServiceImpl implements TScheduleService {
    @Resource
    private TProductScheduleMapper tProductScheduleMapper;
    @Resource
    private TProductMapper tProductMapper;
    @Resource
    private TProductOrderMapper tProductOrderMapper;
    @Resource
    private TProductPlanMapper tProductPlanMapper;
    @Resource
    private TEquipmentMapper tEquipmentMapper;



    @Override
    public int deleteSchedule(Integer id) {

        return tProductScheduleMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TProductSchedule selectschedule(String planSeq, String scheduleSeq) {
        return tProductScheduleMapper.select(planSeq, scheduleSeq);
    }

    @Override
    public List<TProductSchedule> getschedule() {
        List<TProductSchedule> tproductschedules = tProductScheduleMapper.getallschedule();
        for (TProductSchedule tproductschedule : tproductschedules) {
            System.out.println(tproductschedule.toString());
        }
        return tproductschedules;
    }

    @Override
    public ArrayList<TProductSchedule> getallByPage(Page<TProductSchedule> page) {
        return tProductScheduleMapper.getallByPage(page);
    }

    @Override
    public int addSchedule(TProductSchedule tProductSchedule) {
        tProductSchedule.setScheduleSeq("W" + TimeStamp.getTimeStamp());
        tProductSchedule.setFactoryId(1);
        tProductSchedule.setScheduleStatus(1);

        return tProductScheduleMapper.insertSelective(tProductSchedule);
    }

    @Override
    public int updateSchedule(TProductSchedule tProductSchedule) {
        return tProductScheduleMapper.updateByPrimaryKeySelective(tProductSchedule);
    }

    @Override
    public TProductSchedule getByID(int id) {
        return tProductScheduleMapper.selectByPrimaryKey(id);
    }


    @Override
    public int changeStatus(TProductSchedule tProductSchedule) {

        return tProductScheduleMapper.updateByPrimaryKeySelective(tProductSchedule);
    }

    @Override
    public ArrayList<TProductPlan> getPlans(int id) { return tProductPlanMapper.selectByFactoryId(id); }

    @Override
    public ArrayList<TProduct> getProducts(int id) {
        return tProductMapper.selectByFactoryId(id);
    }

    @Override
    public ArrayList<TEquipment> getEquipments(int id) { return  tEquipmentMapper.getEquipment(id); }

    @Override
    public ArrayList<TProductSchedule> searchByPage(Page<TProductSchedule> page) {
        return tProductScheduleMapper.searchByPage(page);
    }
}
package com.nobugs.service;

import com.nobugs.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface TScheduleService {

    int deleteSchedule(Integer id);

    TProductSchedule selectschedule(String planSeq,String scheduleSeq);

    List<TProductSchedule> getschedule();


    int addSchedule(TProductSchedule tProductSchedule);

    int updateSchedule(TProductSchedule tProductSchedule);

    int changeStatus(TProductSchedule tProductSchedule);

    TProductSchedule getByID(int id);

    ArrayList<TProductPlan> getPlans(int id);

    ArrayList<TEquipment> getEquipments(int id);

    ArrayList<TProduct> getProducts(int id);

    ArrayList<TProductSchedule> getallByPage(Page<TProductSchedule> page);

    /*查询*/
    ArrayList<TProductSchedule> searchByPage(Page<TProductSchedule> page);


}

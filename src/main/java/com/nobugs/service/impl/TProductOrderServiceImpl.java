package com.nobugs.service.impl;

import com.nobugs.dao.*;
import com.nobugs.entity.*;
import com.nobugs.service.TProductOrderService;
import com.nobugs.util.TimeStamp;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.SessionScope;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service("tProductOrderService")
public class TProductOrderServiceImpl implements TProductOrderService {

    @Resource
    private TProductOrderMapper tProductOrderMapper;
    @Resource
    private TProductMapper tProductMapper;
    @Resource
    private TProductPlanMapper tProductPlanMapper;
    @Resource
    private TProductScheduleMapper tProductScheduleMapper;
    @Resource
    private TDailyWorkMapper tDailyWorkMapper;


    @Override
    public List<TProductOrder> getAllOrder() {
        return tProductOrderMapper.selectAll();
    }

    @Override
    public int orderStatus(TProductOrder tProductOrder) {
        return tProductOrderMapper.updateByPrimaryKeySelective(tProductOrder);
    }

    @Override
    public int completeOrder(TProductOrder tProductOrder) {
        System.out.println("cxll" + tProductOrder.getId());
        TProductPlan tProductPlan = tProductPlanMapper.selectByOrderId(tProductOrder.getId());
//        System.out.println("cxl" + tProductPlan.getId());
        tProductPlan.setPlanStatus(2);
        tProductPlanMapper.updateByPrimaryKeySelective(tProductPlan);
        tProductScheduleMapper.updateCom(tProductPlan.getId());
        return tProductOrderMapper.updateByPrimaryKeySelective(tProductOrder);
    }

    @Override
    public TProductOrder getByID(int id) {
        return tProductOrderMapper.selectByPrimaryKey(id);
    }


    @Override
    public ArrayList<TProductOrder> searchByPage(Page<TProductOrder> page) {
        return tProductOrderMapper.searchByPage(page);
    }

    @Override
    public ArrayList<TProductOrder> getAllByPage(Page<TProductOrder> page) {
        return tProductOrderMapper.selectAllByPage(page);
    }

    @Override
    public ArrayList<TProduct> getProducts(int id) {
        return tProductMapper.selectByFactoryId(id);
    }

    @Override
    public int addOrder(TProductOrder tProductOrder) {

        tProductOrder.setOrderSeq("O" + TimeStamp.getTimeStamp());
        tProductOrder.setOrderStatus(3);

        return tProductOrderMapper.insertSelective(tProductOrder);
    }

    /*预处理出所有的产能*/
    @Override
    public ArrayList<ProductYield> getYields(int factoryId) {
        return tProductOrderMapper.selectYield(factoryId);
    }

    @Override
    public int toPlan(TProductPlan tProductPlan, TProductOrder tProductOrder) {
        System.out.println(1111);
        tProductPlan.setPlanSeq("P" + TimeStamp.getTimeStamp());
        tProductPlan.setPlanStatus(3);
        tProductOrder.setOrderStatus(5);
        if(tProductOrderMapper.updateByPrimaryKeySelective(tProductOrder) != 1){
            return 0;
        }
        return tProductPlanMapper.insertSelective(tProductPlan);
    }

    @Override
    public HashMap<String, Object> checkOrder(TProductOrder tProductOrder) {

        //获得计划、调度、跟踪
        TProductPlan tProductPlan;
        tProductPlan = tProductPlanMapper.selectByOrderId(tProductOrder.getId());
        List<TProductSchedule> schedules = tProductScheduleMapper.selectByPlanId(tProductPlan.getId());
        List<TDailyWork> dailyWorks = tDailyWorkMapper.selectByPlanId(tProductPlan.getId());

        //放入hashmap
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("plan",tProductPlan);
        resultMap.put("schedules",schedules);
        resultMap.put("dailyWorks",dailyWorks);

        return resultMap;

    }



}

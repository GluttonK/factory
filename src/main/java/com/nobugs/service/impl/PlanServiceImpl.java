package com.nobugs.service.impl;

import com.nobugs.dao.TProductMapper;
import com.nobugs.dao.TProductOrderMapper;
import com.nobugs.dao.TProductPlanMapper;
import com.nobugs.dao.TProductScheduleMapper;
import com.nobugs.entity.*;
import com.nobugs.service.PlanService;
import com.nobugs.util.TimeStamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service("planService")
public class PlanServiceImpl implements PlanService {
    @Resource
    private TProductPlanMapper tProductPlanMapper;
    @Resource
    private TProductMapper tProductMapper;
    @Resource
    private TProductOrderMapper tProductOrderMapper;
    @Resource
    private TProductScheduleMapper tProductScheduleMapper;


    @Override
    public int deleteplan(Integer id) {

        return tProductPlanMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TProductPlan selectplan(String planSeq, String orderSeq) {
        return tProductPlanMapper.select(planSeq,orderSeq);
    }

    @Override
    public List<TProductPlan> getplan() {
        List<TProductPlan> tproductplans=tProductPlanMapper.getallplan();
        for (TProductPlan tproductplan :tproductplans) {
            System.out.println(tproductplan.toString());
        }
        return tproductplans;
    }

    @Override
    public ArrayList<TProductPlan> getallByPage(Page<TProductPlan> page) {
        return tProductPlanMapper.getallByPage(page);
    }

    @Override
    public ArrayList<TProductPlan> searchByPage(Page<TProductPlan> page) {
        return tProductPlanMapper.searchByPage(page);
    }

    @Override
    public int addPlan(TProductPlan tProductPlan) {
        tProductPlan.setPlanSeq("P" + TimeStamp.getTimeStamp());
        tProductPlan.setFactoryId(1);
        tProductPlan.setPlanStatus(3);

        return tProductPlanMapper.insertSelective(tProductPlan);
    }

    @Override
    public int updatePlan(TProductPlan tProductPlan) {
        return tProductPlanMapper.updateByPrimaryKeySelective(tProductPlan);
    }

    @Override
    public TProductPlan getBtID(int id) {
        return tProductPlanMapper.selectByPrimaryKey(id);
    }


    @Override
    public int planStatus(TProductPlan tProductPlan) {
        return tProductPlanMapper.updateByPrimaryKeySelective(tProductPlan);
    }

    @Override
    public int addSchedule(TProductSchedule tProductSchedule) {
        tProductSchedule.setScheduleSeq("S" + TimeStamp.getTimeStamp());
        tProductSchedule.setFactoryId(1);
        tProductSchedule.setScheduleStatus(1);

        return tProductScheduleMapper.insertSelective(tProductSchedule);
    }

    @Override
    public ArrayList<TProduct> getProducts(int id) {
        return tProductMapper.selectByFactoryId(id);
    }




    @Override
    public ArrayList<TProductOrder> getOrders() {
        return (ArrayList<TProductOrder>) tProductOrderMapper.selectAll();
    }
}

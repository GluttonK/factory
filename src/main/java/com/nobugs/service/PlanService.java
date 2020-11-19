package com.nobugs.service;

import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;
import com.nobugs.entity.TProductPlan;
import com.nobugs.entity.TProductSchedule;

import java.util.ArrayList;
import java.util.List;

public interface PlanService {

    int deleteplan(Integer id);

    TProductPlan selectplan(String planSeq, String orderSeq);

    List<TProductPlan> getplan();

    ArrayList<TProductPlan> getallByPage(Page<TProductPlan> page);

    ArrayList<TProductPlan> searchByPage(Page<TProductPlan> page);

    int addPlan(TProductPlan tProductPlan);

    int addSchedule(TProductSchedule tProductSchedule);

    int updatePlan(TProductPlan tProductPlan);

    int planStatus(TProductPlan tProductPlan);

    TProductPlan getBtID(int id);


    ArrayList<TProduct> getProducts(int id);



    ArrayList<TProductOrder> getOrders();
}

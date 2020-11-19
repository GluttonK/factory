package com.nobugs.service;

import com.nobugs.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TProductOrderService {

    List<TProductOrder> getAllOrder();
    ArrayList<TProductOrder> getAllByPage(Page<TProductOrder> page);

    ArrayList<TProductOrder> searchByPage(Page<TProductOrder> page);

    HashMap<String,Object> checkOrder(TProductOrder tProductOrder);

    ArrayList<TProduct> getProducts(int id);

    TProductOrder getByID(int id);

    int addOrder(TProductOrder tProductOrder);

    int toPlan(TProductPlan tProductPlan, TProductOrder tProductOrder);

    int orderStatus(TProductOrder tProductOrder);
    int completeOrder(TProductOrder tProductOrder);

    ArrayList<ProductYield> getYields(int factoryId);
}

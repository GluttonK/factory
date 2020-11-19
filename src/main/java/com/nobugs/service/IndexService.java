package com.nobugs.service;

import com.nobugs.entity.OStatusData;
import com.nobugs.entity.Page;
import com.nobugs.entity.TEquipment;

import java.util.ArrayList;


public interface IndexService {

    /*首页使用，查找某一个状态的订单的个数*/
    ArrayList<OStatusData> getNumByStatus();

    /*分页获取设备*/
    ArrayList<TEquipment> getEquipmentsByPage(Page<Integer> pages);

    /*首页使用，获取某一个月的订单个数*/
    int[] getNumByMonth();

    /*首页使用，获取设备工作状态*/
    int[] getEquipmentStatus();
}

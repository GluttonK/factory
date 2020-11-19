package com.nobugs.service;

import com.nobugs.entity.Page;
import com.nobugs.entity.TEquipment;


import java.util.ArrayList;
import java.util.List;

public interface TEquipmentService {
    List<TEquipment> getEquipment();
    ArrayList<TEquipment> getEquipment(Page<TEquipment> page);
    ArrayList<TEquipment> getAllByPage(Page<TEquipment> page);
    ArrayList<TEquipment>  selectProductsPage(Page<TEquipment> page);
    void deleteByEquipmentId(int equipmentid);
    int Insertfive(TEquipment record);
    void updateEquipmentById(TEquipment tEquipment);
    void updateStatusById(TEquipment tEquipment);
    ArrayList<TEquipment> getProductName(Page<TEquipment> page);
    TEquipment selectBySeq(String seq);
}


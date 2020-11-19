package com.nobugs.service;

import com.nobugs.entity.TEquipment;
import com.nobugs.entity.TEquipmentProduct;

public interface TEquipmentProductService {
    int Insertfive(TEquipmentProduct record);
    int updatefive(TEquipmentProduct record);
    void deleteByEquipmentId(int equipmentid);
}

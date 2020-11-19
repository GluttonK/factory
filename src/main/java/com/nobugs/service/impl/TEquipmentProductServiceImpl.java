package com.nobugs.service.impl;

import com.nobugs.dao.TEquipmentProductMapper;
import com.nobugs.entity.TEquipment;
import com.nobugs.entity.TEquipmentProduct;
import com.nobugs.service.TEquipmentProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tEquipmentProductService")
public class TEquipmentProductServiceImpl implements TEquipmentProductService {
    @Resource
    private TEquipmentProductMapper tEquipmentProductMapper;
    @Override
    public int Insertfive(TEquipmentProduct record){

        return tEquipmentProductMapper.insertSelective(record);

    }
    @Override
    public  int updatefive(TEquipmentProduct record){
        return tEquipmentProductMapper.updatefive(record);
    }
    @Override
    public  void   deleteByEquipmentId(int equipmentid){

         tEquipmentProductMapper.deleteByEquipmentId(equipmentid);
    }
}

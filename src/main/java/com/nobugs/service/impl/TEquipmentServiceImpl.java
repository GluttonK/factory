package com.nobugs.service.impl;

import com.nobugs.dao.TEquipmentMapper;
import com.nobugs.entity.Page;
import com.nobugs.entity.TEquipment;
import com.nobugs.entity.TProduct;
import com.nobugs.service.TEquipmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("tEquipmentService")
public class TEquipmentServiceImpl implements TEquipmentService {
    @Resource
    private TEquipmentMapper tEquipmentMapper;
    @Override
    public ArrayList<TEquipment> getEquipment(Page<TEquipment> page){
        ArrayList<TEquipment> tEquipments=tEquipmentMapper.selectByEquipmentSeqPage(page);
        return  tEquipments;
    }
    @Override
    public List<TEquipment> getEquipment(){

        List<TEquipment> tEquipment=tEquipmentMapper.selectAllEquipment();

        return  tEquipment;
    }
    @Override
    public ArrayList<TEquipment> getAllByPage(Page<TEquipment> page) {
        return tEquipmentMapper.selectAllByPage(page);
    }
    @Override
    public ArrayList<TEquipment> selectProductsPage(Page<TEquipment> page) {
        return tEquipmentMapper.selectProductsPage(page);
    }
    @Override
    public void deleteByEquipmentId(int  equipmentid){
        tEquipmentMapper.deleteByEquipmentId( equipmentid);

    }
    @Override
    public int Insertfive(TEquipment record){

        return tEquipmentMapper.insertfive(record);

    }
    @Override
    public  void updateEquipmentById(TEquipment tEquipment){

        tEquipmentMapper.updateById(tEquipment);

    }
    @Override
    public  TEquipment selectBySeq(String seq){
        return   tEquipmentMapper.selectBySeq(seq);
    }
    @Override public void updateStatusById(TEquipment tEquipment){
        tEquipmentMapper.updateStatusById(tEquipment);
    }
    @Override
    public ArrayList<TEquipment> getProductName(Page<TEquipment> page){
        ArrayList<TEquipment> tEquipments=tEquipmentMapper.selectProductNamePage(page);
        return  tEquipments;
    }


}

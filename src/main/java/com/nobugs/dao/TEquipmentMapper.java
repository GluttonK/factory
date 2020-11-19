package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TEquipment;
import com.nobugs.entity.TProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TEquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEquipment record);
    int insertfive(TEquipment record);

    int insertSelective(TEquipment record);

    TEquipment selectByPrimaryKey(Integer id);

//    TEquipment selectProductName(Integer id);
    ArrayList<TEquipment> selectProductNamePage(Page<TEquipment> page);
    ArrayList<TEquipment> selectByEquipmentSeqPage(Page<TEquipment> page);
    ArrayList<TEquipment>    selectProductsPage(Page<TEquipment> page);
    List<TEquipment> selectAllEquipment();

    void deleteByEquipmentId(@Param(value = "equipmentid") int equipmentid);

    ArrayList<TEquipment> selectByFactoryId(@Param(value = "factoryId") Integer id);

    /*工单创建时使用，扩展产品名称和id*/
    ArrayList<TEquipment> getEquipment(@Param(value = "factoryId") Integer id);

    void updateById( TEquipment tEquipment);
    void updateStatusById(TEquipment tEquipment);

    int updateByPrimaryKeySelective(TEquipment record);

    ArrayList<TEquipment> selectAllByPage(Page<TEquipment> page);

    int updateByPrimaryKey(TEquipment record);
    TEquipment selectBySeq(String seq);



    /*首页做图片*/
    ArrayList<TEquipment> getEquipmentByPage(Page<Integer> pages);
}
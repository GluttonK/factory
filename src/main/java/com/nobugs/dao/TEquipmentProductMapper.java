package com.nobugs.dao;

import com.nobugs.entity.TEquipmentProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TEquipmentProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEquipmentProduct record);

    int insertSelective(TEquipmentProduct record);

    TEquipmentProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TEquipmentProduct record);

    int updateByPrimaryKey(TEquipmentProduct record);
    int updatefive(TEquipmentProduct record);
    int deleteByEquipmentId(Integer id);

}
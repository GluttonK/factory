package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.ProductYield;
import com.nobugs.entity.TProductOrder;
import com.nobugs.entity.TProductPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TProductOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProductOrder record);

    int insertSelective(TProductOrder record);

    TProductOrder selectByPrimaryKey(Integer id);

    ArrayList<TProductOrder> selectAllByPage(Page<TProductOrder> page);

    //分页查询只能有一个参数page对象
    ArrayList<TProductOrder> searchByPage(Page<TProductOrder> page);

    int selectNumByStatus(@Param(value = "orderStatus") int orderStatus);

    ArrayList<TProductOrder> selectAll();

    ArrayList<ProductYield> selectYield(@Param(value = "factoryId") int factoryId);

    int updateByPrimaryKeySelective(TProductOrder record);

    int updateByPrimaryKey(TProductOrder record);
}
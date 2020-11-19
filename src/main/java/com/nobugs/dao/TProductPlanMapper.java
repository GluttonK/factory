package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;
import com.nobugs.entity.TProductPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TProductPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProductPlan record);

    int insertSelective(TProductPlan record);

    TProductPlan select(String planSeq,String orderSeq);

    List<TProductPlan> getallplan();

    ArrayList<TProductPlan> getallByPage(Page<TProductPlan> page);

    ArrayList<TProductPlan> searchByPage(Page<TProductPlan> page);

    TProductPlan selectByPrimaryKey(Integer id);

    TProductPlan selectByOrderId(@Param(value = "orderId") Integer orderId);

    int updateByPrimaryKeySelective(TProductPlan record);

    int updateByPrimaryKey(TProductPlan record);

    ArrayList<TProductPlan> selectByFactoryId(@Param(value = "factoryId") Integer id);

}
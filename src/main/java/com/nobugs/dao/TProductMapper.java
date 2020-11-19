package com.nobugs.dao;

import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProduct record);
    int insertfour(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Integer id);
    ArrayList<TProduct> selectByProductNamePage(Page<TProduct> page);
    List<TProduct> selectAllProduct();
    void deleteByProductId(@Param(value = "productid") int productid);
    ArrayList<TProduct> selectByFactoryId(@Param(value = "factoryId") Integer id);
    void updateById( TProduct tproduct);
    int updateByPrimaryKeySelective(TProduct record);
    ArrayList<TProduct> selectAllByPage(Page<TProduct> page);
    int updateByPrimaryKey(TProduct record);
}
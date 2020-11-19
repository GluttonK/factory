package com.nobugs.service.impl;

import com.nobugs.dao.TProductMapper;
import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;
import com.nobugs.service.TProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("tProductService")
public class TProductServiceImpl implements TProductService {
    @Resource
    private TProductMapper tProductMapper;

    @Override
    public ArrayList<TProduct> getProduct(Page<TProduct> page){
        ArrayList<TProduct> tProducts=tProductMapper.selectByProductNamePage(page);
         return  tProducts;
    }
    @Override
    public List<TProduct> getProduct(){

        List<TProduct> tProducts=tProductMapper.selectAllProduct();

        return  tProducts;
    }
    @Override
    public ArrayList<TProduct> getAllByPage(Page<TProduct> page) {
        return tProductMapper.selectAllByPage(page);
    }
    @Override
    public void deleteByProductId(int productid){
        tProductMapper.deleteByProductId(productid);

    }
    @Override
    public int Insertfour(TProduct record){

        return tProductMapper.insertfour(record);

    }
    @Override
    public  void updateProductById(TProduct tProduct){

        tProductMapper.updateByPrimaryKey(tProduct);

    }


}

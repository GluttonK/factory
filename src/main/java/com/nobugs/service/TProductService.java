package com.nobugs.service;

import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;

import java.util.ArrayList;
import java.util.List;

public interface TProductService {
    List<TProduct> getProduct();
    ArrayList<TProduct> getProduct(Page<TProduct> page);
    ArrayList<TProduct> getAllByPage(Page<TProduct> page);
    void deleteByProductId(int productid);
    int Insertfour(TProduct record);
    void updateProductById(TProduct tProduct);
}

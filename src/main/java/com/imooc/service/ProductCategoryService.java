package com.imooc.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * @Author Calvin
 * @Date 2019-7-18 23:26
 * @Version 1.0
 **/
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findProductCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}

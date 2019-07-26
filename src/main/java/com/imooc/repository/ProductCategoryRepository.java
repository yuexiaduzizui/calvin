package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Calvin
 * @Date 2019-7-18 0:38
 * @Version 1.0
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryId);
}

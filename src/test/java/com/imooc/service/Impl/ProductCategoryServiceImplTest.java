package com.imooc.service.Impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-18 23:34
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(1);
        assertNotNull(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findProductCategoryTypeIn() {
        List<ProductCategory> productCategoryTypeIn = productCategoryService.findProductCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        assertNotEquals(0,productCategoryTypeIn.size());

    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生专享");
        productCategory.setCategoryType(11);
        ProductCategory save = productCategoryService.save(productCategory);
        assertNotNull(save);
    }
}
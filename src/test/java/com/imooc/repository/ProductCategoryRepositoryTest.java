package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Calvin
 * @Date 2019-7-18 0:40
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindOn() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    public void testInsert() {
        ProductCategory productCategory = new ProductCategory("老婆最爱",88);
        ProductCategory save = productCategoryRepository.save(productCategory);
        System.out.println(save);
    }

    @Test
    public void testSelectList() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        System.out.println(productCategoryList);
    }

    @Test
    public void testUpdate() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryId(1);
        productCategory.setCategoryType(3);
        ProductCategory save = productCategoryRepository.save(productCategory);
        System.out.println(save);
    }

    @Test
    public void testdyncDynamicUpdateTime() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryType(20);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void testFindByCategoryType() {
        List<Integer> categoryIdList = Arrays.asList(1, 3, 5);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryIdIn(categoryIdList);
        Assert.assertNotEquals(0,productCategoryList.size());
        System.out.println(productCategoryList);
    }
}
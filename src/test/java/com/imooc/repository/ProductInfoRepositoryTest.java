package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:01
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void testSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("我是A货");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductName("A货");
        productInfo.setProductPrice(new BigDecimal(12));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(12);
        ProductInfo save = productInfoRepository.save(productInfo);
        assertNotNull(save);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        assertNotEquals(0,productInfoList);
    }
}
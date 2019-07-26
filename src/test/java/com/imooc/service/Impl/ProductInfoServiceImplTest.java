package com.imooc.service.Impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:34
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123456");
        assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        System.out.println(productInfoPage);
        List<ProductInfo> contentList = productInfoPage.getContent();
        assertNotEquals(0,contentList.size());

    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductStock(13);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductPrice(new BigDecimal(66));
        productInfo.setProductName("皮皮虾");
        productInfo.setProductIcon("http://ooxx.jpg");
        productInfo.setProductDescription("皮皮虾,我们走");
        productInfo.setCategoryType(2);
        ProductInfo save = productInfoService.save(productInfo);
        assertNotNull(save);
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoService.onSale("123456");
        assertEquals(ProductStatusEnum.UP,productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoService.offSale("123456");
        assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatusEnum());
    }
}
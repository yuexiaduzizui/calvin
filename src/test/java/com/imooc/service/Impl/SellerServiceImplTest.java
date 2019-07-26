package com.imooc.service.Impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-26 11:34
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        String openid = "1564111652491802413";
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        assertNotNull(sellerInfo);
    }
}
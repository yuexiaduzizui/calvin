package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import com.imooc.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-26 11:23
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void testSave(){

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("root");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid(KeyUtil.getUniqueKey());
        SellerInfo save = sellerInfoRepository.save(sellerInfo);
        assertNotNull(save);

    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("1564111652491802413");
        System.out.println(sellerInfo);
        assertNotNull(sellerInfo);
    }
}
package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-19 22:49
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("654321");
        orderMaster.setBuyerName("小师妹");
        orderMaster.setBuyerAddress("百度网");
        orderMaster.setBuyerOpenid("7758");
        orderMaster.setBuyerPhone("13437620260");
        orderMaster.setOrderAmount(new BigDecimal(24));
        OrderMaster save = orderMasterRepository.save(orderMaster);
        assertNotNull(save);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("7758", pageRequest);
        assertNotEquals(0,result.getTotalElements());
    }
}
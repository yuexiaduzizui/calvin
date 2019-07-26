package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
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
 * @Date 2019-7-19 23:25
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("2132432");
        orderDetail.setProductIcon("http://oxxo.jpg");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.3));
        orderDetail.setProductQuantity(23);
        orderDetail.setProductId("54543353453");
        OrderDetail save = orderDetailRepository.save(orderDetail);
        assertNotNull(save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("2132432");
        assertNotEquals(0,orderDetailList.size());
    }
}
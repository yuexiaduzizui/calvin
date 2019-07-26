package com.imooc.service.Impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDto;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Calvin
 * @Date 2019-7-20 19:12
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("大师兄");
        orderDto.setBuyerAddress("慕课网");
        orderDto.setBuyerPhone("13437628899");
        orderDto.setBuyerOpenid("110110");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(3);

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto saveOrderDto = orderService.create(orderDto);
        log.info("[创建订单] result={}", saveOrderDto);
        assertNotNull(saveOrderDto);
    }

    @Test
    public void findOne() {

        String orderId = "1563629704299309727";
        OrderDto orderDto = orderService.findOne(orderId);
        log.info("[订单查询结果为]:{}",orderDto);
        assertEquals("1563629704299309727",orderDto.getOrderId());
    }

    @Test
    public void findList() {

        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList("110110", pageRequest);
        assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() {

        String orderId = "1563629704299309727";
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto cancel = orderService.cancel(orderDto);
        assertEquals(OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        String orderId = "1563629704299309727";
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto finishOrderDto = orderService.finish(orderDto);
        assertEquals(OrderStatusEnum.FINISH.getCode(),finishOrderDto.getOrderStatus());
    }

    @Test
    public void paid() {
        String orderId = "1563629704299309727";
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto paidOrderDto = orderService.paid(orderDto);
        assertEquals(PayStatusEnum.SUCCESS.getCode(),paidOrderDto.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        assertNotEquals(0,orderDtoPage.getTotalElements());
    }
}
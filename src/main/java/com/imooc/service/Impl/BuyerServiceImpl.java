package com.imooc.service.Impl;

import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author Calvin
 * @Date 2019-7-21 21:34
 * @Version 1.0
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {

        OrderDto orderDto = orderService.findOne(orderId);
        if (null == orderDto) {
            return null;
        }
        if (!openid.equals(orderDto.getBuyerOpenid())) {
            log.error("[查询订单] 订单的openid不一致, openid={},orderDto={}",openid,orderDto);
            throw new SellException(ResultEnum.OPENID_NOT_FIT);
        }
        return orderService.cancel(orderDto);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {

        OrderDto orderDto = checkOrderOwner(openid, orderId);
        Optional.ofNullable(orderDto).orElseThrow(() -> new SellException(ResultEnum.ORDER_NOT_EXT));
        return orderDto;
    }
}

package com.imooc.service;

import com.imooc.dto.OrderDto;

/**
 * @Author Calvin
 * @Date 2019-7-21 21:31
 * @Version 1.0
 **/
public interface BuyerService {

    /**
     *  查询一个订单
     */
    OrderDto findOrderOne(String openid, String orderId);

    /**
     *  取消订单
     */
    OrderDto cancelOrder(String openid, String orderId);
}

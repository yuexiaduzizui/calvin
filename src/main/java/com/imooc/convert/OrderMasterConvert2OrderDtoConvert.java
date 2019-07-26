package com.imooc.convert;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Calvin
 * @Date 2019-7-20 22:12
 * @Version 1.0
 **/
public class OrderMasterConvert2OrderDtoConvert {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        List<OrderDto> orderDtoList = orderMasterList.stream().map(x -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(x, orderDto);
            return orderDto;
        }).collect(Collectors.toList());
        return orderDtoList;
    }
}

package com.imooc.service.Impl;

import com.imooc.convert.OrderMasterConvert2OrderDtoConvert;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import com.imooc.dto.OrderDto;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.OrderService;
import com.imooc.service.ProductInfoService;
import com.imooc.utils.CommonStringUtil;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Calvin
 * @Date 2019-7-20 15:54
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto create(OrderDto orderDto) {

        List<BigDecimal>  orderAmountTotalList = new ArrayList<>();
        String orderId = KeyUtil.getUniqueKey();

        //1. 查询商品(数量,价格)
        orderDto.getOrderDetailList().forEach(x -> {
            ProductInfo productInfo = productInfoRepository.findOne(x.getProductId());
            Optional.ofNullable(productInfo).orElseThrow(() ->  new SellException(ResultEnum.PRODUCT_NOT_EXT));

            //2. 计算订单总价
            BigDecimal orderAmount = productInfo.getProductPrice().
                    multiply(new BigDecimal(x.getProductQuantity()));
            orderAmountTotalList.add(orderAmount);

            //3. 订单详情入库
            x.setOrderId(orderId);
            x.setDetailId(KeyUtil.getUniqueKey());
            BeanUtils.copyProperties(productInfo,x);
            OrderDetail save = orderDetailRepository.save(x);
        });

        //4. 写入订单数据库(orderMaster,orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        BigDecimal orderAmount = orderAmountTotalList.stream().reduce(BigDecimal::add).get();
        orderMaster.setOrderAmount(orderAmount);
        OrderMaster save = orderMasterRepository.save(orderMaster);

        //5. 扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(x ->
                new CartDto(x.getProductId(),x.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {

        //查询订单信息
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        Optional.ofNullable(orderMaster).orElseThrow(() -> new SellException(ResultEnum.ORDER_NOT_EXT));
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CommonStringUtil.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXT);
        }

        //查询订单明细信息
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtoList = OrderMasterConvert2OrderDtoConvert.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto cancel(OrderDto orderDto) {

        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("[取消订单] 订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        OrderMaster orderMaster = new OrderMaster();
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        Optional.ofNullable(updateOrderMaster).orElseThrow(() -> {
            log.error("[取消订单]更新失败,orderMaster={}",orderMaster);
            return  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        });

        //返回库存
        if (CommonStringUtil.isEmpty(orderDto.getOrderDetailList())) {
            log.error("[取消订单] 订单中无商品详情,orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDERDETAIL_IS_EMPTY);
        }

        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);

        //如果已支付,需要退款
        if (PayStatusEnum.SUCCESS.getCode().equals(orderDto.getOrderStatus())) {
            //TODO
        }

        return orderDto;
    }



    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto finish(OrderDto orderDto) {

        //1. 判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("[完结订单] 订单状态不正确, orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        Optional.ofNullable(updateOrderMaster).orElseThrow(() -> {
            log.error("[完结订单] 更新失败,orderMaster={}",orderMaster);
            return new SellException(ResultEnum.ORDER_STATUS_ERROR);
        });
        return orderDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto paid(OrderDto orderDto) {

        //1. 判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("[订单支付完成] 订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 判断支付状态
        if (!PayStatusEnum.WAIT.getCode().equals(orderDto.getPayStatus())) {
            log.error("[订单支付] 支付状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }

        //3. 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        Optional.ofNullable(updateOrderMaster).orElseThrow(() -> {
            log.error("[订单支付] 订单更新失败,orderMaster={}",orderMaster);
           return new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        });
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList = OrderMasterConvert2OrderDtoConvert.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
    }
}

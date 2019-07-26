package com.imooc.controller;

import com.imooc.convert.OrderForm2OrderDtoConverter;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.CommonStringUtil;
import com.imooc.utils.ResultVoUtil;
import com.imooc.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Calvin
 * @Date 2019-7-21 3:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     *  创建订单
     */
    @PostMapping("/create")
    public ResultVo <Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确, orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CommonStringUtil.isEmpty(orderDto.getOrderDetailList())) {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_IS_EMPTY);
        }

        OrderDto createOrderDto = orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createOrderDto.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size) {
        if (CommonStringUtil.isEmpty(openid)) {
            log.info("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.OPENID_IS_EMPTY);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVoUtil.success(orderDto);
    }

    /**
     *  取消订单
     */
    @PutMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }

}

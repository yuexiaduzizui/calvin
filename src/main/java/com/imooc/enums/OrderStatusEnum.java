package com.imooc.enums;

import lombok.Getter;

/**
 * @Author Calvin
 * @Date 2019-7-19 22:22
 * @Version 1.0
 **/
@Getter
public enum OrderStatusEnum implements CodeEnum {

    /**
     * 订单状态
     * 0:新订单
     * 1:完结
     * 2:已取消
     */
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"已取消"),
        ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}

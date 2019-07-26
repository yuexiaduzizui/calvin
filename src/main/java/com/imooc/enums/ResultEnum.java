package com.imooc.enums;

import lombok.Getter;

/**
 * @Author Calvin
 * @Date 2019-7-20 16:03
 * @Version 1.0
 **/
@Getter
public enum ResultEnum {

    /**
     * 产品提示信息
     */
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXT(1001,"商品不存在"),
    PRODUCT_STOCK_ERROR(1002,"库存不足"),
    ORDER_NOT_EXT(1003,"订单不存在"),
    ORDERDETAIL_NOT_EXT(1004,"订单明细不存在"),
    ORDER_STATUS_ERROR(1005,"订单状态不正确"),
    ORDER_UPDATE_FAIL(1006,"订单更新失败"),
    ORDERDETAIL_IS_EMPTY(1007,"订单详情为空"),
    PAY_STATUS_ERROR(1008,"订单支付状态不正确"),
    CART_IS_EMPTY(1009,"购物车不能为空"),
    OPENID_IS_EMPTY(1010,"openid为空"),
    OPENID_NOT_FIT(1011,"订单的openid不一致"),
    ORDER_CANCEL_SUCCESS(1012,"订单取消成功"),
    ORDER_FINISH_SUCCESS(1012,"订单完结成功"),
    PRODUCT_STATUS_ERRROR(1013,"商品状态不正确"),
    PRODUCT_UP_SUCCESS(1014,"商品上架成功"),
    PRODUCT_DOWN_SUCCESS(1015,"商品下架成功"),
    PRODUCT_SUBMIT_SUCCESS(1016,"商品信息提交成功"),
    CATEGORY_SUBMIT_SUCCESS(1016,"商品类目提交成功"),
    ACTIVITY_END(1017,"活动结束了"),
    REDIS_LOCK_ERROR(1018,"哎呦喂,人也太多了,换个姿势再试试吧!"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.imooc.dto;

import lombok.Data;

/**
 * @Author Calvin
 * @Date 2019-7-20 16:48
 * @Version 1.0
 **/
@Data
public class CartDto {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

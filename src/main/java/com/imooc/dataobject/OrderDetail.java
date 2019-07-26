package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author Calvin
 * @Date 2019-7-19 22:36
 * @Version 1.0
 **/
@Entity
@Data
public class OrderDetail {

    /**
     * 订单明细ID
     */
    @Id
    private String detailId ;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 产品数量
     */
    private Integer productQuantity;

    /**
     * 产品小图
     */
    private String productIcon;

}

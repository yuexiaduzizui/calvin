package com.imooc.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Calvin
 * @Date 2019-7-24 23:34
 * @Version 1.0
 **/
@Data
public class ProductForm {

    /**
     * 产品ID
     */
    public String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 产品库存
     */
    private Integer productStock;

    /**
     * 产品描述
     */
    private String productDescription;

    /**
     * 产品小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}

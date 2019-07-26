package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.CodeEnum;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Calvin
 * @Date 2019-7-18 23:44
 * @Version 1.0
 **/
@Entity
@DynamicUpdate
@Data
public class ProductInfo  {

    /**
     * 产品ID
     */
    @Id
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
     * 状态:
     * 0:正常
     * 1:下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

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

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}

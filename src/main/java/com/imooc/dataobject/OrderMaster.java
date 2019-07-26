package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Calvin
 * @Date 2019-7-19 23:10
 * @Version 1.0
 **/
@Data
@Entity
public class OrderMaster {

        /**
         * 订单ID
         */
        @Id
        private String orderId;

        /**
         * 买家名字
         */
        private String buyerName;

        /**
         * 买家手机号
         */
        private String buyerPhone;

        /**
         * 买家地址
         */
        private String buyerAddress;

        /**
         * 买家微信openId
         */
        private String buyerOpenid;

        /**
         * 订单总金额
         */
        private BigDecimal orderAmount;

        /**
         * 订单状态:
         * 默认为:0(新下单)
         */
        private Integer orderStatus = OrderStatusEnum.NEW.getCode();

        /**
         * 支付状态:
         * 默认为0(未支付)
         */
        private Integer payStatus = PayStatusEnum.WAIT.getCode();

        /**
         * 创建时间
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date createTime;

        /**
         * 更新时间
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date updateTime;

}

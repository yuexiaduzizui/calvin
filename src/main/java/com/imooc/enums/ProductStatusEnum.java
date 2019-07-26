package com.imooc.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:20
 * @Version 1.0
 **/
@Getter
@NoArgsConstructor
public enum ProductStatusEnum implements CodeEnum{

    /**
     * 产品状态:
     * 0:正常
     * 1:下架
     */
    UP(0,"正常"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.imooc.enums;

import lombok.Getter;

/**
 * @Author Calvin
 * @Date 2019-7-19 22:32
 * @Version 1.0
 **/
@Getter
public enum PayStatusEnum implements CodeEnum {

    /**
     * 支付状态:
     * 0:等待支付
     * 1:支付成功
     */
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

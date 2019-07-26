package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author Calvin
 * @Date 2019-7-20 16:02
 * @Version 1.0
 **/
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }

}

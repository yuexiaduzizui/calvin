package com.imooc.handler;

import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellException;
import com.imooc.utils.ResultVoUtil;
import com.imooc.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Calvin
 * @Date 2019-7-26 12:52
 * @Version 1.0
 **/
@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handerSellerException(SellException e) {
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handlerBankException() {

    }
}

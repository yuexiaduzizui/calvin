package com.imooc.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @Author Calvin
 * @Date 2019-7-19 0:59
 * @Version 1.0
 **/
@Getter
@Setter
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 4196874033508619186L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;


}

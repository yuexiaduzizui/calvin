package com.imooc.utils;

import com.imooc.vo.ResultVo;

/**
 * @Author Calvin
 * @Date 2019-7-19 21:33
 * @Version 1.0
 **/
public class ResultVoUtil {

    private ResultVoUtil() {};

    public static ResultVo success(Object object) {

        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code,String msg) {

        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }
}

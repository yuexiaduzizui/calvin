package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @Author Calvin
 * @Date 2019-7-23 21:34
 * @Version 1.0
 **/
public class EnumUtil {

    public static <T extends CodeEnum>  T getByCode(Integer code, Class<T> enumClass) {
        for (T t : enumClass.getEnumConstants()) {
            if (code.equals(t.getCode())) {
                return t;
            }
        }
        return null;
    }
}

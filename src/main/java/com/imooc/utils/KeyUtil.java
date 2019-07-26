package com.imooc.utils;

import java.util.Random;

/**
 * @Author Calvin
 * @Date 2019-7-20 16:23
 * @Version 1.0
 **/
public class KeyUtil {

    private KeyUtil() {};

    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        System.currentTimeMillis();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}

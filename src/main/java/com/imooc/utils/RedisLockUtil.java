package com.imooc.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author Calvin
 * @Date 2019-7-26 16:39
 * @Version 1.0
 **/
@Component
@Slf4j
public class RedisLockUtil {


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *  加锁
     *
     * @param key 商品id
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value) {

        if (redisTemplate.opsForValue().setIfAbsent(key,value)) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (CommonStringUtil.isNotEmpty(currentValue)  &&
            Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(CommonStringUtil.isNotEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }

        }
        return false;
    }

    public void unLock(String key,String value) {

        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (CommonStringUtil.isNotEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis分布式锁] 解锁发生异常:{}",e);
        }

    }
}

package com.imooc.service.Impl;

import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.SecSkillService;
import com.imooc.utils.KeyUtil;
import com.imooc.utils.RedisLockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Calvin
 * @Date 2019-7-26 13:21
 * @Version 1.0
 **/
@Service
public class SecSkillServiceImpl implements SecSkillService {

    /**
     * 国庆特价:皮蛋粥特价,限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    @Autowired
    private RedisLockUtil redisLockUtil;

    public static final int TIMEOUT = 1 * 1000;

    /**
     *  模拟多个表:商品信息表,库存表,秒杀成功订单表
     */
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    @Override
    public String queryMap(String productId) {
        return "国庆活动,皮蛋粥特检,限量份:  " + products.get(productId) + "  还剩:  " + stock.get(productId) + "份  "
                + "该商品成功下单用户为:  " + orders.size() + "人";
    }

    @Override
    public String queruSecSkillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public /*synchronized*/ void orderProductMockDiffUser(String productId) {

        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLockUtil.lock(productId, String.valueOf(time))) {
            throw new SellException(ResultEnum.REDIS_LOCK_ERROR);
        }

        try {
            Integer stockNum = stock.get(productId);
            if (0 == stockNum) {
                throw new SellException(ResultEnum.ACTIVITY_END);
            } else {
                //下单
                orders.put(KeyUtil.getUniqueKey(), productId);
                //减库存
                stockNum = stockNum - 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stock.put(productId, stockNum);
            }
        } finally {
            //解锁
            redisLockUtil.unLock(productId, String.valueOf(time));
        }
    }
}

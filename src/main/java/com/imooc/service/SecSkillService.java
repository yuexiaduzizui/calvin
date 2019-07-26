package com.imooc.service;

/**
 * @Author Calvin
 * @Date 2019-7-26 13:20
 * @Version 1.0
 **/
public interface SecSkillService {

    /**
     *  查询秒杀活动特价商品信息
     *
     * @param productId
     * @return
     */
    String queryMap(String productId);

    /**
     *  秒杀,没有抢到获得"哎呦喂,手慢了",抢到了,返回剩余的库存数量
     *
     * @param productId
     * @return
     */
    void orderProductMockDiffUser(String productId);

    /**
     *  根据商品id查询商品信息
     *
     * @return
     */
    String queruSecSkillProductInfo(String productId);
}

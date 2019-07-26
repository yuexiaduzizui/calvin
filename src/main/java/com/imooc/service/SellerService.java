package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * @Author Calvin
 * @Date 2019-7-26 11:31
 * @Version 1.0
 **/
public interface SellerService {

    /**
     *  通过openid查询卖家端信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}

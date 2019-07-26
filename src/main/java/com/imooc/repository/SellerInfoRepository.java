package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Calvin
 * @Date 2019-7-26 11:21
 * @Version 1.0
 **/
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);

}

package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import org.omg.CORBA.StringHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:14
 * @Version 1.0
 **/
public interface ProductInfoService {

    /**
     *  根据商品id查询单个商品
     *
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     *  保存商品
     *
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     *  加库存
     *
     * @param cartDtoList
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDto> cartDtoList);

    /**
     *  商品上架
     *
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     *  商品下架
     *
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}

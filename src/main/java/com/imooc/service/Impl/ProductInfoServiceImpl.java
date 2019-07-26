package com.imooc.service.Impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.lang.model.SourceVersion;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:17
 * @Version 1.0
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void increaseStock(List<CartDto> cartDtoList) {

        cartDtoList.stream().forEach(x -> {
            ProductInfo productInfo = productInfoRepository.findOne(x.getProductId());
            Optional.ofNullable(productInfo).orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXT));
            Integer result = productInfo.getProductStock() + x.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        });

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void decreaseStock(List<CartDto> cartDtoList) {

        cartDtoList.forEach(x -> {
            ProductInfo productInfo = productInfoRepository.findOne(x.getProductId());
            Optional.ofNullable(productInfo).orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXT));
            Integer result = productInfo.getProductStock() - x.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            ProductInfo save = productInfoRepository.save(productInfo);
        });

    }

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);
        Optional.ofNullable(productInfo).orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXT));
        if(ProductStatusEnum.UP.equals(productInfo.getProductStatusEnum())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERRROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo save = productInfoRepository.save(productInfo);
        return save;
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);
        Optional.ofNullable(productInfo).orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXT));
        if(ProductStatusEnum.DOWN.equals(productInfo.getProductStatusEnum())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERRROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        ProductInfo save = productInfoRepository.save(productInfo);
        return save;
    }
}

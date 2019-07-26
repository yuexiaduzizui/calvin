package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductInfoService;
import com.imooc.utils.ResultVoUtil;
import com.imooc.vo.ProductInfoVo;
import com.imooc.vo.ProductVo;
import com.imooc.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Calvin
 * @Date 2019-7-19 0:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "#sellerId",condition = "#sellerId.length() > 3",unless = "#result.getCode() != 0")
    public ResultVo list(@RequestParam("sellerId") String sellerId) {

        //查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream().map(x -> x.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findProductCategoryTypeIn(categoryTypeList);
        List<ProductVo> productVoList = new ArrayList<>();
        productCategoryList.forEach(x -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(x,productVo);

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            productInfoList.forEach(y -> {
                if (x.getCategoryType().equals(y.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(y,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            });
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        });

        //数据拼装
        return ResultVoUtil.success(productVoList);
    }
}
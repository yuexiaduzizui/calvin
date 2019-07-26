package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.form.ProductForm;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductInfoService;
import com.imooc.utils.CommonStringUtil;
import com.imooc.utils.KeyUtil;
import com.sun.org.apache.regexp.internal.REUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  卖家端商品
 * @Author Calvin
 * @Date 2019-7-24 12:54
 * @Version 1.0
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     *
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map) {

        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/list",map);
    }

    /**
     *  上架
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object>map) {

        try {
            productInfoService.onSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg", ResultEnum.PRODUCT_UP_SUCCESS.getMessage());
        return new  ModelAndView("common/success",map);
    }

    /**
     *  商品下架
     *
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String,Object> map) {

        try {
            productInfoService.offSale(productId);
        } catch (Exception e) {
            map.put("url","/sell/seller/product/list");
            map.put("msg",e.getMessage());
            return new ModelAndView("/common/error");
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.PRODUCT_DOWN_SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map) {

        if (!CommonStringUtil.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("/product/index",map);
    }

    /**
     *  保存和更新
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }

        try {
            ProductInfo productInfo = new ProductInfo();
            if (CommonStringUtil.isNotEmpty(form.getProductId())) {
                productInfo = productInfoService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productInfoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.PRODUCT_SUBMIT_SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }
}

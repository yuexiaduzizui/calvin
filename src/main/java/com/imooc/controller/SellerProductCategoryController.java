package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.enums.ResultEnum;
import com.imooc.form.CategoryForm;
import com.imooc.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @Author Calvin
 * @Date 2019-7-25 0:34
 * @Version 1.0
 **/
@Controller
@RequestMapping("/seller/category")
public class SellerProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public ModelAndView list(Map<String,Object>map) {

        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("/category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map) {

        if (null != categoryId && categoryId > 0) {
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("/category/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map) {

        if (bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("/common/error",map);
        }

        try {
            ProductCategory productCategory = new ProductCategory();
            if (null != form.getCategoryId()) {
                productCategory = productCategoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);
            productCategoryService.save(productCategory);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        map.put("msg", ResultEnum.CATEGORY_SUBMIT_SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }
}

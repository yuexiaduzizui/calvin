package com.imooc.controller;

import com.imooc.service.SecSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Calvin
 * @Date 2019-7-26 13:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecSkillController {

    @Autowired
    private SecSkillService secSkillService;

    /**
     *   查询秒杀活动特价商品信息
     *
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable("productId") String productId) {
        return secSkillService.queruSecSkillProductInfo(productId);
    }

    /**
     *  秒杀
     *
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable("productId")String productId) {
        log.info("@skill request, productId: "+productId);
        secSkillService.orderProductMockDiffUser(productId);
        return secSkillService.queruSecSkillProductInfo(productId);
    }
}

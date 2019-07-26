package com.imooc.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author Calvin
 * @Date 2019-7-25 0:55
 * @Version 1.0
 **/
@Data
public class CategoryForm {

    /**
     * 类目ID
     */
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}

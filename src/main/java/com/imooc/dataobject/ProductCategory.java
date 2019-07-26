package com.imooc.dataobject;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author Calvin
 * @Date 2019-7-18 0:26
 * @Version 1.0
 **/
@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
public class ProductCategory {

    /**
     * 类目ID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  修改时间
     */
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}

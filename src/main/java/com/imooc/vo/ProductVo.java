package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Calvin
 * @Date 2019-7-19 1:13
 * @Version 1.0
 **/
@Data
public class ProductVo implements Serializable {


    private static final long serialVersionUID = -4307578760133746983L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}

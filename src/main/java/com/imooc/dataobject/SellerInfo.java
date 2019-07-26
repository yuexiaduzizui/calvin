package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author Calvin
 * @Date 2019-7-26 11:18
 * @Version 1.0
 **/
@Data
@Entity
public class SellerInfo {

    /**
     * 数据库主键
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * openid
     */
    private String openid;

}

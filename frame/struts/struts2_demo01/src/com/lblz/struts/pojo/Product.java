package com.lblz.struts.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/27 23:54
 **/
@Data
@AllArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private String productDesc;
    private Double productPrice;
}

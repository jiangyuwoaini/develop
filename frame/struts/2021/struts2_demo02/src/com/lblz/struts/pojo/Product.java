package com.lblz.struts.pojo;


import lombok.*;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/27 23:54
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private String productDesc;
    private Double productPrice;
    public String save(){
        System.out.println("save:={}"+this);
        return "details";
    }
}

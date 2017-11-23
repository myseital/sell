package com.mao.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by myseital  on 2017/11/23.
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;
}

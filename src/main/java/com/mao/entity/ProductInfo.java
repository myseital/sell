package com.mao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mao.common.enums.PayStatus;
import com.mao.common.enums.ProductStatus;
import com.mao.common.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus = ProductStatus.UP.getCode();

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductInfo() {
    }

    @JsonIgnore
    public ProductStatus getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatus.class);
    }
}

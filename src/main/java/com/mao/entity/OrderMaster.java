package com.mao.entity;

import com.mao.common.enums.OrderStatus;
import com.mao.common.enums.PayStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by myseital  on 2017/11/20.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态 默认为0新下单 */
    private Integer orderStatus = OrderStatus.NEW.getCode();

    /** 支付状态 默认为0未支付 */
    private Integer payStatus = PayStatus.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    public OrderMaster() {
    }
}

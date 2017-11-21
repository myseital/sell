package com.mao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mao.common.serializer.Date2LongSerializer;
import com.mao.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by myseital  on 2017/11/20.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态 默认为0新下单 */
    private Integer orderStatus;

    /** 支付状态 默认为0未支付 */
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    //返回的json中需要list数据，但实际没有数据  不能用null表示 需要用数组表示[]
    //private List<OrderDetail> orderDetailList = new ArrayList<>();
}

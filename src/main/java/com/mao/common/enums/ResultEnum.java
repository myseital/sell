package com.mao.common.enums;

import lombok.Getter;

/**
 * Created by myseital  on 2017/11/20.
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_INSUFFICIENT(11, "商品库存不足"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态错误"),

    ORDER_STATUS_UPDATE_FAIL(15, "订单状态更新错误"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态错误"),

    PARAM_ERROR(1, "参数错误"),

    CART_EMPTY(18, "购物车为空");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

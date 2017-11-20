package com.mao.common.enums;

import lombok.Getter;

/**
 * Created by myseital  on 2017/11/20.
 */
@Getter
public enum OrderStatus {
    NEW(0, "新订单"),
    FINISH(1, "完成"),
    CANCEL(2, "已取消");

    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

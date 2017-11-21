package com.mao.service;

import com.lly835.bestpay.model.PayResponse;
import com.mao.dto.OrderDTO;

/**
 * Created by myseital  on 2017/11/21.
 */
public interface PayService {

    /**
     * 支付订单
     * @param orderDTO 被支付的订单对应的orderDTO对象
     */
    PayResponse create(OrderDTO orderDTO);
}

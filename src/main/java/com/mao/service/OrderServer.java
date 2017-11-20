package com.mao.service;

import com.mao.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by myseital  on 2017/11/20.
 */
public interface OrderServer {

    /**
     * 创建订单
     * @param orderDTO 创建的orderDTO对象
     * @return 创建好的OrderDTO
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @param orderId 订单id
     * @return 查询到的OrderDTO
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     * @param buyerOpenid 买家id
     * @param pageable 分页查询请求类型
     * @return 分页对象
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO 被取消的orderDTO对象
     * @return 取消之后的OrderDTO
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param orderDTO 被完成的订单OrderDTo对象
     * @return 完成好的订单OrderDTo对象
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO 被支付的订单OrderDTO对象
     * @return 支付完的订单OrderDTO对象
     */
    OrderDTO paid(OrderDTO orderDTO);
}

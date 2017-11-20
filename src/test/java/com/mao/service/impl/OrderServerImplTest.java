package com.mao.service.impl;

import com.mao.common.enums.OrderStatus;
import com.mao.common.enums.PayStatus;
import com.mao.dto.OrderDTO;
import com.mao.entity.OrderDetail;
import com.mao.entity.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by myseital  on 2017/11/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServerImplTest {

    @Autowired
    private OrderServerImpl orderServer;

    private final String OPENID = "110";

    private final String ORDER_ID = "1511158382628832268";


    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("mao");
        orderDTO.setBuyerAddress("hzdy");
        orderDTO.setBuyerPhone("12345678911");
        orderDTO.setBuyerOpenid(OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(10);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(5);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderServer.create(orderDTO);
        log.info("[创建订单] result = {}", result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderServer.findOne(ORDER_ID);
        log.info("[查询单个订单] result - {}", orderDTO);
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0, 6);
        Page<OrderDTO> orderDTOPage = orderServer.findList(OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderServer.findOne(ORDER_ID);
        OrderDTO result = orderServer.cancel(orderDTO);
        Assert.assertEquals(OrderStatus.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderServer.findOne(ORDER_ID);
        OrderDTO result = orderServer.finish(orderDTO);
        Assert.assertEquals(OrderStatus.FINISH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderServer.findOne(ORDER_ID);
        OrderDTO result = orderServer.paid(orderDTO);
        Assert.assertEquals(PayStatus.SUCCESS.getCode(), result.getPayStatus());
    }

}
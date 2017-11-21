package com.mao.controller;

import com.lly835.bestpay.model.PayResponse;
import com.mao.common.enums.ResultEnum;
import com.mao.dto.OrderDTO;
import com.mao.exception.SellException;
import com.mao.service.OrderServer;
import com.mao.service.PayService;
import freemarker.ext.beans.HashAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by myseital  on 2017/11/21.
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private PayService payService;

    @GetMapping("/createTest")
    public ModelAndView createTest() {
        return new ModelAndView("pay/create1");
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl) {
        // 1.查询订单
        OrderDTO orderDTO = orderServer.findOne(orderId);
        if (orderDTO == null) {
            log.error("[支付订单] 查询订单出错 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("returnUrl", returnUrl);
        map.put("payResponse", payResponse);

        return new ModelAndView("pay/create", map);
    }
}

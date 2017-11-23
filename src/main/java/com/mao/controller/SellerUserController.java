package com.mao.controller;

import com.mao.common.enums.ResultEnum;
import com.mao.entity.SellerInfo;
import com.mao.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.Map;

/**
 * 卖家用户
 * Created by myseital  on 2017/11/23.
 */
@Controller
@RequestMapping("/seller/user")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                      Map<String, Object> map) {

        // 1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        // 2.设置token至redis
        redisTemplate.opsForValue().set("abc", "zzzz");

        // 3.设置token至cookies


        return new ModelAndView("common/succes", map);
    }

    @GetMapping("/logout")
    public void logout() {

    }
}

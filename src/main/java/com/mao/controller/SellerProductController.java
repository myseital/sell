package com.mao.controller;

import com.mao.entity.ProductInfo;
import com.mao.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by myseital  on 2017/11/22.
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        Map<String, Object> map = new HashMap();
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("product/list", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam(value = "productId") String productId) {
        productInfoService.offSale(productId);

        return new ModelAndView("product/list");
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam(value = "productId") String productId) {
        productInfoService.onSale(productId);

        return new ModelAndView("product/list");
    }
}

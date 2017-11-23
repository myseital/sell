package com.mao.service;

import com.mao.entity.SellerInfo;

/**
 * Created by myseital  on 2017/11/23.
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}

package com.mao.repository;

import com.mao.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by myseital  on 2017/11/23.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String>{

    SellerInfo findByOpenid(String openid);
}

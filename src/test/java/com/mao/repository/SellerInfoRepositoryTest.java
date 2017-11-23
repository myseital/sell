package com.mao.repository;

import com.mao.entity.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by myseital  on 2017/11/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("112");
        sellerInfo.setOpenid("112");
        sellerInfo.setPassword("admin");
        sellerInfo.setUsername("admin");

        SellerInfo result =  repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOpenid() throws Exception {

        SellerInfo result = repository.findByOpenid("112");
        Assert.assertNotNull(result);
    }

}
package com.mao.repository;

import com.mao.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("apple");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(1000);
        productInfo.setProductDescription("good");
        productInfo.setProductIcon("http://xxxxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        repository.save(productInfo);
    }
}
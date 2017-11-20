package com.mao.repository;

import com.mao.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by myseital  on 2017/11/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567892");
        orderDetail.setOrderId("111");
        orderDetail.setProductIcon("http://xxxxxx.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("banana");
        orderDetail.setProductPrice(new BigDecimal(5.6));
        orderDetail.setProductQuantity(10);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrOrderIdTest() {
        List<OrderDetail> result = repository.findByOrderId("111");
        Assert.assertNotEquals(0, result.size());
    }

}
package com.mao.service.impl;

import com.mao.common.utils.KeyUtil;
import com.mao.exception.SellException;
import com.mao.service.RedisLock;
import com.mao.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {

    private static final int TIME_OUT = 10 * 1000;//超时时间

    @Autowired
    private RedisLock redisLock;

    /**
     * 双11活动 特价活动 限量10000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static {
        /**
         * 模拟多个表,商品信息表 库存表 秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);
        stock.put("123456", 10000);
    }

    private String queryMap(String productId) {
        return "双11活动，商品特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + " 份"
                + orders.size() + " 人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public synchronized void orderProductMockDiffUser(String productId) {

        //synchronized 能解决并发的问题
        //1. 无法做到细粒度控制
        //2. 只适合单点的情况

        //加锁
        long time = System.currentTimeMillis() + TIME_OUT;
        if (redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "哎呦喂,人也太多了,换个姿势再试试");
        }

        // 1.查询该商品库存 为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            // 2.下单(模拟不同的用户openid不同)
            orders.put(KeyUtil.genUniqueKey(), productId);
            // 3.减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //解锁
        redisLock.unlock(productId, String.valueOf(time));
    }
}

package com.songjz.seckill;

import com.songjz.seckill.mapper.OrderMapper;
import com.songjz.seckill.pojo.Goods;
import com.songjz.seckill.pojo.Order;
import com.songjz.seckill.service.IGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeckillApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void contextLoads() {
//        Order o1 = orderMapper.selectById(1);
//        System.out.println(o1);

    }

}

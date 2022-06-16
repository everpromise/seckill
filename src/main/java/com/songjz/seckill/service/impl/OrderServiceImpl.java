package com.songjz.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songjz.seckill.mapper.OrderMapper;
import com.songjz.seckill.mapper.SeckillGoodsMapper;
import com.songjz.seckill.pojo.Order;
import com.songjz.seckill.pojo.SeckillGoods;
import com.songjz.seckill.pojo.SeckillOrder;
import com.songjz.seckill.pojo.User;
import com.songjz.seckill.service.IOrderService;
import com.songjz.seckill.service.ISeckillGoodsService;
import com.songjz.seckill.service.ISeckillOrderService;
import com.songjz.seckill.vo.GoodsVo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-06-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;


    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    /**
     *@描述 秒杀实现
     *@参数
     *@返回值
     *@创建人 songjz
     *@创建时间 2022/6/12
     *@修改人和其它信息
     */

    @Override
    public Order seckill(User user, GoodsVo goods) {
        // 秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        // 生成订单

        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        System.out.println(order);
        orderMapper.insert(order);
        // 生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);

        return order;
    }
}

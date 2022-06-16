package com.songjz.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songjz.seckill.pojo.Order;
import com.songjz.seckill.pojo.User;
import com.songjz.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-06-09
 */
public interface IOrderService extends IService<Order> {

    /**
     *@描述 秒杀
     *@参数
     *@返回值
     *@创建人 songjz
     *@创建时间 2022/6/12
     *@修改人和其它信息
     */
    
    Order seckill(User user, GoodsVo goods);
}

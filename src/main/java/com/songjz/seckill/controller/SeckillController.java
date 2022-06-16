package com.songjz.seckill.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.songjz.seckill.pojo.Order;
import com.songjz.seckill.pojo.SeckillOrder;
import com.songjz.seckill.pojo.User;
import com.songjz.seckill.service.IGoodsService;
import com.songjz.seckill.service.IOrderService;
import com.songjz.seckill.service.ISeckillOrderService;
import com.songjz.seckill.vo.GoodsVo;
import com.songjz.seckill.vo.RespBeanNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀
 * <p>
 * 浙江大学： 计算机科学与技术学院 CAPG实验室
 * 联系方式： 15043763729
 *
 * @author songjz
 * @since 2022/6/12
 */

@Controller
@RequestMapping("/secKill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;


    /**
     *@描述 秒杀压测
     * windows QPS: 3738
     * linux QPS:
     *@参数
     *@返回值
     *@创建人 songjz
     *@创建时间 2022/6/16
     *@修改人和其它信息
     */

    @RequestMapping("/doSecKill")
    public String doSeckill(Model model, User user, Long goodsId) {
        if(user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        // 判断库存
        if (goods.getGoodsStock()<1) {
            model.addAttribute("errmsg", RespBeanNum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        // 判断是否重复抢购
        SeckillOrder secKillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (secKillOrder != null) {
            model.addAttribute("errmsg", RespBeanNum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }
}

package com.songjz.seckill.controller;

import com.songjz.seckill.pojo.User;
import com.songjz.seckill.service.IGoodsService;
import com.songjz.seckill.service.UserService;
import com.songjz.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserService userService;
    @Autowired
    private IGoodsService iGoodsService;

    // 原始 requestmapping
//    @RequestMapping("/toList")
//    public String toList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
//        System.out.println("session: " + session);
//        System.out.println("model: " + model);
//        System.out.println("ticket: " + ticket);
//        if (StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        System.out.println("goods: " + user);
//        if (null == user) {
//            return "login";
//        }
//        model.addAttribute("user", user);
//        return "goodsList";
//    }

    /**
     *@描述 跳转商品列表页
     * windows 优化前QPS：4904
     * linux 优化前QPS: 184
     *@参数
     *@返回值
     *@创建人 songjz
     *@创建时间 2022/6/14
     *@修改人和其它信息
     */

    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        System.out.println(user);
        model.addAttribute("goodsList", iGoodsService.findGoodsVo());
        return "goodsList";
    }

    @RequestMapping("/toDetail")
    public String toDetail(Model model, User user) {
        model.addAttribute("user", user);
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        System.out.println(goodsId);
        model.addAttribute("user", user);
        GoodsVo goodsVo = iGoodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        System.out.println(goodsVo);
        System.out.println(nowDate.getTime());
        System.out.println(LocalDateTime.now());

//        秒杀状态
        int secKillStatus = 0;
        int remainSeconds;
        // 秒杀还未开始
        if (nowDate.before(startDate)){
            remainSeconds = ((int)((startDate.getTime() - nowDate.getTime()) / 1000));
        } else if (nowDate.after(endDate)) {
            // 秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        }else {
            // 秒杀中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        System.out.println(secKillStatus);
        System.out.println(nowDate);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }
}

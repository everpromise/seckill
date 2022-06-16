package com.songjz.seckill.controller;


import com.songjz.seckill.pojo.User;
import com.songjz.seckill.vo.RespBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-05-31
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    /**
     *@描述 用户信息测试
     *@参数 
     *@返回值 
     *@创建人 songjz
     *@创建时间 2022/6/13
     *@修改人和其它信息
     */
    
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

}

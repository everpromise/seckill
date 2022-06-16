package com.songjz.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songjz.seckill.exception.GlobalException;
import com.songjz.seckill.mapper.OrderMapper;
import com.songjz.seckill.mapper.UserMapper;
import com.songjz.seckill.pojo.Order;
import com.songjz.seckill.pojo.User;
import com.songjz.seckill.service.UserService;
import com.songjz.seckill.utils.CookieUtil;
import com.songjz.seckill.utils.MD5Util;
import com.songjz.seckill.utils.UUIDUtil;
import com.songjz.seckill.vo.LoginVo;
import com.songjz.seckill.vo.RespBean;
import com.songjz.seckill.vo.RespBeanNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
////        参数校验
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanNum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanNum.MOBILE_ERROR);
//        }

        // 根据手机号获取用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", mobile);
        List<User> userList = userMapper.selectList(userQueryWrapper);
        User user = userList.get(0);
        if (null == user) {
            throw new GlobalException(RespBeanNum.LOGIN_ERROR);
        }
//        判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSlat()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanNum.LOGIN_PASSWORD_ERROR);
        }
//        生成cookie
        String ticket = UUIDUtil.uuid();
        System.out.println("本地cookie： " + ticket);
        // 将用户信息存入redis中
//        redisTemplate.opsForValue().set("user:" + ticket, user);
//        redisTemplate = redisConfig.redisTemplate(new RedisConnectionFactory() {
//            @Override
//            public RedisConnection getConnection() {
//                return null;
//            }
//
//            @Override
//            public RedisClusterConnection getClusterConnection() {
//                return null;
//            }
//
//            @Override
//            public boolean getConvertPipelineAndTxResults() {
//                return false;
//            }
//
//            @Override
//            public RedisSentinelConnection getSentinelConnection() {
//                return null;
//            }
//
//            @Override
//            public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
//                return null;
//            }
//        });
        redisTemplate.opsForValue().set("user:" + ticket, user);

//        request.getSession().setAttribute(ticket, user);

        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            System.out.println("getUserByCookie error: userTicket null");
            return null;
        }
        System.out.println(userTicket);
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
//        System.out.println("getuserbycookie user: " + user);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }

        return user;
    }
}

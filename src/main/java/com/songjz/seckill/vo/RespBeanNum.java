package com.songjz.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanNum {
    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务器异常"),

    //登录
    LOGIN_ERROR(500210, "用户名或密码为空"),
    LOGIN_PASSWORD_ERROR(500211, "密码错误"),
    MOBILE_ERROR(500212, "手机号码不正确"),
    BIND_ERROR(500213, "参数校验异常: "),

    //秒杀模块
    EMPTY_STOCK(500500, "库存不足"),
    REPEATE_ERROR(500501, "该商品没人限购一件");


    private final Integer code;
    private final String message;
}

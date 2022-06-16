package com.songjz.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object obj;

    public static RespBean success() {
        return new RespBean(RespBeanNum.SUCCESS.getCode(), RespBeanNum.SUCCESS.getMessage(), null);
    }

    public static RespBean success(Object obj) {
        return new RespBean(RespBeanNum.SUCCESS.getCode(), RespBeanNum.SUCCESS.getMessage(), obj);
    }

    public static RespBean error(RespBeanNum respBeanNum) {
        return new RespBean(respBeanNum.getCode(), respBeanNum.getMessage(), null);
    }

    public static RespBean error(RespBeanNum respBeanNum, Object obj) {
        return new RespBean(respBeanNum.getCode(), respBeanNum.getMessage(), obj);
    }
}

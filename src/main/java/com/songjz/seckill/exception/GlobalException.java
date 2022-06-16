package com.songjz.seckill.exception;

import com.songjz.seckill.vo.RespBeanNum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanNum respBeanNum;
}

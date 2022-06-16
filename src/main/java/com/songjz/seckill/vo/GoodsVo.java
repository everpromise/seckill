package com.songjz.seckill.vo;

import com.songjz.seckill.pojo.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品返回对象
 * <p>
 * 浙江大学： 计算机科学与技术学院 CAPG实验室
 * 联系方式： 15043763729
 *
 * @author songjz
 * @since 2022/6/9
 */

@Data
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}

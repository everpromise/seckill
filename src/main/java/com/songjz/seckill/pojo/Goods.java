package com.songjz.seckill.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-06-09
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品详情
     */
    private String goodsDetail;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品库存，-1表示没有限制
     */
    private Integer goodsStock;


}

package com.songjz.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songjz.seckill.pojo.Goods;
import com.songjz.seckill.vo.GoodsVo;

import java.util.List;

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
public interface IGoodsService extends IService<Goods> {
    
    /**
     *@描述 获取商品列表
     *@参数
     *@返回值
     *@创建人 songjz
     *@创建时间 2022/6/9
     *@修改人和其它信息
     */
    
    List<GoodsVo> findGoodsVo();

    /**
     * @描述 获取商品详情
     * @参数
     * @返回值
     * @创建人 songjz
     * @创建时间 2022/6/9
     * @修改人和其它信息
     */
    
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}

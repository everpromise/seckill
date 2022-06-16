package com.songjz.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songjz.seckill.mapper.GoodsMapper;
import com.songjz.seckill.pojo.Goods;
import com.songjz.seckill.service.IGoodsService;
import com.songjz.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @since 2022-06-09
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    /**
     *@描述 获取商品列表
     *@参数 
     *@返回值 
     *@创建人 songjz
     *@创建时间 2022/6/9
     *@修改人和其它信息
     */
    
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}

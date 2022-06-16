package com.songjz.seckill.mapper;

import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.songjz.seckill.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * 浙江大学：计算机科学与技术学院 capg实验室
 * 2022级 宋金洲
 *
 * @author songjz
 * @since 2022-05-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

package com.bite.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bite.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo> {
    @Select("SELECT sleep(#{time})")
    Integer slowQuery(Integer time);
}

package com.wisdom.framework.sharding.mapper;

import com.wisdom.framework.sharding.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Order> selectByOrderIdBetween(@Param("startOrderId") Long startOrderId, @Param("endOrderId") Long endOrderId);

    List<Order> selectByDateBetween(@Param("start_date") String startDate, @Param("end_date") String endDate);

    int insertOrder(Order order);

}

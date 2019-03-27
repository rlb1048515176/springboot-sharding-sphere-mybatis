package com.wisdom.framework.sharding;

import com.wisdom.framework.sharding.entity.Order;
import com.wisdom.framework.sharding.entity.OrderItem;
import com.wisdom.framework.sharding.mapper.OrderItemMapper;
import com.wisdom.framework.sharding.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingApplicationTests {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void contextLoads() {
        insertOrder();
        orderMapper.selectByDateBetween("2017-01-01","2018-01-01");
    }

    @Transactional
    public void insertOrder(){
        Order order = new Order(2L,"2017-01-01","1");
        orderMapper.insertOrder(order);
        System.out.println("order主键："+ order.getOrderId());
        OrderItem orderItem = new OrderItem(null,order.getOrderId(),"2017-01-01","2");
        orderItemMapper.insertOrderItem(orderItem);
        System.out.println("orderItem主键："+ orderItem.getOrderItemId());
    }
}

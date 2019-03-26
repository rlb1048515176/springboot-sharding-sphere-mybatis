package com.wisdom.framework.sharding;

import com.wisdom.framework.sharding.entity.Order;
import com.wisdom.framework.sharding.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingApplicationTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void contextLoads() {
        orderMapper.insertOrder(new Order(1L,"2017-01-01","1"));
        orderMapper.insertOrder(new Order(2L,"2018-01-02","1"));
        orderMapper.selectByOrderIdBetween(1L,2L);
    }

}

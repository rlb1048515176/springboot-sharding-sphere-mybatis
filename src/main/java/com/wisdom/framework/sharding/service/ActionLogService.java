package com.wisdom.framework.sharding.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wisdom.framework.sharding.entity.ActionLog;
import com.wisdom.framework.sharding.entity.Order;
import com.wisdom.framework.sharding.mapper.OrderMapper;
import com.wisdom.framework.sharding.sql.SqlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
@Transactional
public class ActionLogService {

    @Autowired
    SqlMapper sqlMapper;
    @Autowired
    private OrderMapper orderMapper;

    public Object selectAll(String startPage,String pageLimit,String beginDate,String endDate,String name){
        List<Order> list = new ArrayList();
        if(!StringUtils.isNotBlank(name)){
            name = "";
        }
        if(StringUtils.isNotBlank(startPage)&&StringUtils.isNotBlank(startPage)){
            Page page = PageHelper.startPage(Integer.parseInt(startPage), Integer.parseInt(pageLimit), true);
            list = orderMapper.selectByDateBetween(beginDate,endDate);
         //   System.out.println("total :"+page.getTotal());
            HashMap hm = new HashMap();
            hm.put("total", page.getTotal());
            hm.put("data", list);
            return hm;
        }else{
            return orderMapper.selectByDateBetween(beginDate,endDate);
        }
    }

    public Object insertOrder(String id,String date,String status){
        HashMap hm =new HashMap();
        try {
            orderMapper.insertOrder(new Order(Long.parseLong(id), date,status));
            hm.put("code","success");
        }catch ( Exception e) {
            e.printStackTrace();
            hm.put("code", "error");
        }
        return hm;
    }
}

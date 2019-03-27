package com.wisdom.framework.sharding.sql;
import com.wisdom.framework.sharding.entity.ActionLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Mapper
public interface SqlMapper {
    @Select("select * from user_action_log where date=#{date} and id=#{id}")
    List<ActionLog> selectByDate(@Param("date") String date, @Param("id") String id);

    @Select("select * from user_action_log where date between #{begin_date} and #{end_date} and group_id=#{group_id}")
    List<ActionLog> selectAll(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("group_id") String groupId);

    @Insert("insert into user_action_log(id,date,name) values(#{id},#{date},#{name})")
    void saveUserActionLog(@Param("id") long id, @Param("date") String date, @Param("name") String name);

    @Insert("insert into user_action_log_id( date,name) values( #{date},#{name})")
    void saveUserActionLogId(@Param("date") String date, @Param("name") String name);

    @Insert("insert into user_action_log_id( id,date,name) values(#{id}, #{date},#{name})")
    void saveUserActionLogId1(@Param("id") long id, @Param("date") String date, @Param("name") String name);

    //分表查询的结果直接合并在一起输出，没有做合并和排行,distinct只对当个物理表起作用，对多个逻辑表没有效果，
    //举例：在两张物理表中，同一个用户访问网站的次数都为2，相当于两天中该用户访问网站4次，分表查询结果是2，预期结果是1，因为用户数要去重
    @Select("SELECT * FROM t_order WHERE date BETWEEN #{begin_date} AND #{end_date} " )
    List<Map<String,Object>> servSort(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("name") String name);

    @Select("select count(*) as num  from user_action_log where date BETWEEN #{begin_date} AND #{end_date} and id =1 " )
    List<Map<String,Object>> servSortCount(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("name") String name);

    @Select("select distinct serv,name from user_action_log where date BETWEEN #{begin_date} AND #{end_date} and group_id=#{group_id}")
    List<Map<String,Object>> servSortEnhance(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("group_id") String groupId);

    //用户排行，需要重新合并和排行
    @Select("select name,count(*) as num from user_action_log where date between #{begin_date} and #{end_date} and group_id=#{group_id} " +
            "group by name ")
    List<Map<String,Object>> userNameSort(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("group_id") String groupId);

    @Select("select serv,sum(up_flux+down_flux) as total,sum(up_flux) as up_flux,sum(down_flux) as down_flux from " +
            "user_action_log where date between #{begin_date} and #{end_date} and group_id=#{group_id} group by serv")
    List<Map<String,Object>> fluxServSort(@Param("begin_date") String beginDate, @Param("end_date") String endDate, @Param("group_id") String groupId);
}

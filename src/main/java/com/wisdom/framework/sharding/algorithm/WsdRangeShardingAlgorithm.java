package com.wisdom.framework.sharding.algorithm;

import com.google.common.collect.Range;
import com.wisdom.framework.sharding.util.DateUtil;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * 范围分片算法 (此分片策略与PreciseShardingAlgorithm配合使用，可选配置，如果不配置，则按照全库路由处理)
 * 用于处理使用“单一键”作为分片键的“BETWEEN AND”进行分片的场景。需要配合StandardShardingStrategy使用
 *
 */
public class WsdRangeShardingAlgorithm implements RangeShardingAlgorithm<String> {
    public WsdRangeShardingAlgorithm() {
        System.out.println("WsdRangeShardingAlgorithm");
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        System.out.println("WsdRangeShardingAlgorithm.........");
        Collection<String> result = new LinkedHashSet<String>();
        Range range = rangeShardingValue.getValueRange();
        String beginDateStr =  "" + range.lowerEndpoint();
        String endDateStr = "" + range.upperEndpoint();
        getAllDate(result,beginDateStr,endDateStr,rangeShardingValue.getLogicTableName());
        return result;
    }

    public void getAllDate(Collection<String> result,String beginDateStr,String endDateStr,String tableName){
        Date beginDate = DateUtil.stringToDate(beginDateStr,"YYYYMMdd");
        Date endDate = DateUtil.stringToDate(endDateStr,"YYYYMMdd");
        //计算两个日期年份的间隔数
        int years = DateUtil.daysBetweenYears(endDate,beginDate);
        for (int i = 0 ; i <= years ; i++){
            String itemYearStr = DateUtil.getDateBetweenYear(beginDate,i);
            result.add(tableName + "_" + itemYearStr);
        }
    }
}

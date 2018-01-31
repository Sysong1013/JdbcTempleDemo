package com.dangdang.shardingRuler;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by songyisong on 2018/1/29.
 */
public class databaseSharding implements SingleKeyDatabaseShardingAlgorithm<Long> {
    /**
     * sql 中关键字 匹配符为 =的时候，表的路由函数
     */
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        if (shardingValue.getValue() % 2 == 0) {
            return "prodstockdb";
        } else if (shardingValue.getValue() % 2 == 1) {
            return "prodstockevt";
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * sql 中关键字 匹配符为 in 的时候，表的路由函数
     */
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            if (value % 2 == 0) {
                result.add("prodstockdb");
            } else {
                result.add("prodstockevt");
            }
        }
        return result;
    }

    /**
     * sql 中关键字 匹配符为 between的时候，库的路由函数
     */
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            if (i % 2 == 0) {
                result.add("prodstockdb");
            } else {
                result.add("prodstockevt");
            }
        }
        return result;
    }
}

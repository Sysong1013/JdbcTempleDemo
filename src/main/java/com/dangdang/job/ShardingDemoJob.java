package com.dangdang.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by songyisong on 2018/1/29.
 */
@Slf4j
public class ShardingDemoJob implements SimpleJob {
    private static final Logger errorlog = LoggerFactory.getLogger("application.error");

    @Autowired
    RawService rawService;

    public void execute(ShardingContext shardingContext) {
        try {
            rawService.doTest(shardingContext.getShardingItem(), shardingContext.getShardingTotalCount());
        } catch (Exception e) {
            errorlog.error("SimpleJob Get An Exception e:", e);
        }

    }
}

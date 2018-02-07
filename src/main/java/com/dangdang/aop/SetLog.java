package com.dangdang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by songyisong on 2018/2/7.
 */
@Slf4j
@Aspect
@Component
public class SetLog {
    @After("execution(* com.dangdang.rawdao.UpdateStockMapperRaw.updateStock(..))")
    public void updatelog(JoinPoint joinPoint){
        log.info("切面在执行更新操作后写入日志:product_id={},warehouse_id={}",
                Arrays.asList(joinPoint.getArgs()).get(0),Arrays.asList(joinPoint.getArgs()).get(1));
    }
    @After("execution(* com.dangdang.rawdao.InsertStockMapperRaw.insertStock(..))")
    public void insertlog(JoinPoint joinPoint){
        log.info("切面在执行插入操作后写入日志:product_id={},warehouse_id={}",
                Arrays.asList(joinPoint.getArgs()).get(0),Arrays.asList(joinPoint.getArgs()).get(1));
    }
}

package com.dangdang.job;

import com.dangdang.dao.DeleteStockMapper;
import com.dangdang.dao.InsertStockMapper;
import com.dangdang.dao.SelectStockMapper;
import com.dangdang.dao.UpdateStockMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by songyisong on 2018/1/29.
 */
@Service
public class ShardingDemoService {
    @Autowired
    SelectStockMapper selectStockMapper;
    @Autowired
    UpdateStockMapper updateStockMapper;
    @Autowired
    InsertStockMapper insertStockMapper;
    @Autowired
    DeleteStockMapper deleteStockMapper;

    public void doTest(int shardingItem, int shardingTotalCount) {

        long productId = 101l;//以producId分库，producId%2=0的路由到prodstockdb库,producId%2=1的映射到prodstockevt库
        int warehouseId = 10; //以warehouseId分表，warehouseId%2=0的路由到sharding_0表,warehouseId%2=1的映射到sharding_1表,

        System.out.println("成功删除"+deleteStockMapper.deleteStock(productId,warehouseId));//测试删除

        try {
            System.out.println("成功插入" + insertStockMapper.insertStock(productId, warehouseId) + "条数据");//测试插入（品仓唯一键）
        } catch (DuplicateKeyException e) {
            System.out.println("已经存在对应品仓的数据，请勿重复插入");
        }

        System.out.println(selectStockMapper.getStock(productId,warehouseId));//测试查询

        System.out.println("成功更新"+updateStockMapper.updateStock(productId,warehouseId)+"条数据");//测试更新
    }
}

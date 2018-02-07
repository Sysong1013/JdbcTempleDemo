package com.dangdang.job;

import com.dangdang.rawdao.DeleteStockMapperRaw;
import com.dangdang.rawdao.InsertStockMapperRaw;
import com.dangdang.rawdao.SelectStockMapperRaw;
import com.dangdang.rawdao.UpdateStockMapperRaw;
import io.shardingjdbc.core.api.HintManager;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Created by songyisong on 2018/1/31.
 */
@Service
public class RawService {
    @Autowired
    SelectStockMapperRaw selectStockMapperRaw;
    @Autowired
    UpdateStockMapperRaw updateStockMapperRaw;
    @Autowired
    InsertStockMapperRaw insertStockMapperRaw;
    @Autowired
    DeleteStockMapperRaw deleteStockMapperRaw;

    public void doTest(int shardingItem, int shardingTotalCount) {

        //HintManager hintManager = HintManager.getInstance() ;//强制路由至主库，避免主从延迟
        //hintManager.setMasterRouteOnly();

        long productId = 100l;
        int warehouseId = 10; //读写分离我把prodstockdb当成主库，prodstockevt当成从库

        //System.out.println("成功删除" + deleteStockMapperRaw.deleteStock(productId, warehouseId));//测试删除

        try {
            System.out.println("成功插入" + insertStockMapperRaw.insertStock(productId, warehouseId) + "条数据");//测试插入（品仓唯一键）
        } catch (DuplicateKeyException e) {
            System.out.println("已经存在对应品仓的数据，请勿重复插入");
        }

        //System.out.println(selectStockMapperRaw.getStock(productId, warehouseId));//测试查询
        //hintManager.close();
        System.out.println("成功更新" + updateStockMapperRaw.updateStock(productId, warehouseId) + "条数据");//测试更新
    }
}

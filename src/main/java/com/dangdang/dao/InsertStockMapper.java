package com.dangdang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by songyisong on 2018/1/30.
 */
@Service
public interface InsertStockMapper {
    @Insert("INSERT INTO sharding(product_id,warehouse_id,stock_quantity,post_stock_quantity,last_changed_date) " +
            "VALUES (#{productId},#{warehouseId},10,0,NOW())")
    public int insertStock(@Param("productId") long productId, @Param("warehouseId") int warehouseId);
}

package com.dangdang.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * Created by songyisong on 2018/1/30.
 */
@Service
public interface UpdateStockMapper {
    @Update("UPDATE sharding SET last_changed_date=NOW() WHERE product_id=#{productId} AND warehouse_id=#{warehouseId}")
    public int updateStock(@Param("productId") long productId, @Param("warehouseId") int warehouseId);
}

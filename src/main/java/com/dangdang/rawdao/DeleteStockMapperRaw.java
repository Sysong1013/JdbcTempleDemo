package com.dangdang.rawdao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by songyisong on 2018/1/30.
 */
@Service
public interface DeleteStockMapperRaw {
    @Delete("DELETE FROM sharding_0 WHERE product_id=#{productId} AND warehouse_id=#{warehouseId}")
    public int deleteStock(@Param("productId") long productId, @Param("warehouseId") int warehouseId);
}

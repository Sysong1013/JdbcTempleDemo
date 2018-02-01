package com.dangdang.rawdao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * Created by songyisong on 2018/1/30.
 */
@Service
public interface UpdateStockMapperRaw {
    @Update("UPDATE sharding_0 SET last_changed_date=NOW() WHERE product_id=#{productId} AND warehouse_id=#{warehouseId}")
    public int updateStock(@Param("productId") long productId, @Param("warehouseId") int warehouseId);
}

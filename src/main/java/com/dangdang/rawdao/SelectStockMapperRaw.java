package com.dangdang.rawdao;

import com.dangdang.modle.StockModle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by songyisong on 2018/1/30.
 */
@Service
public interface SelectStockMapperRaw {
    @Results({
            @Result(id = true, column = "product_id", property = "productId"),
            @Result(column = "warehouse_id", property = "warehouseId"),
            @Result(column = "stock_quantity", property = "stockQuantity"),
            @Result(column = "post_stock_quantity", property = "postStockQuantity"),
            @Result(column = "last_changed_date", property = "lastChangedDate")
    })
    @Select("SELECT product_id,warehouse_id,stock_quantity,post_stock_quantity,last_changed_date " +
            "FROM sharding_0 where product_id=#{productId} AND warehouse_id=#{warehouseId}")
    public List<StockModle> getStock(@Param("productId") long productId, @Param("warehouseId") int warehouseId);
}

package com.dangdang.modle;

import lombok.Data;

import java.util.Date;

/**
 * Created by songyisong on 2018/1/30.
 */
@Data
public class StockModle {
    private long productId;
    private int warehouseId;
    private int stockQuantity;
    private int postStockQuantity;
    private Date lastChangedDate;
}

package org.example.layeredarchitecture.dto;

import java.io.Serializable;

public class StockDTO implements Serializable {
    private int stockId;
    private int itemId;
    private int qty;
    private String updateInfo;

    public StockDTO(int stockId, int itemId, int qty, String updateInfo) {
        this.stockId = stockId;
        this.itemId = itemId;
        this.qty = qty;
        this.updateInfo = updateInfo;
    }

    public StockDTO() {}

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }
}
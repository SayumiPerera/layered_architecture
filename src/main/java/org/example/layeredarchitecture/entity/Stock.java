package org.example.layeredarchitecture.entity;

public class Stock {

    private int stockId;
    private int itemId;
    private int qty;
    private String updateInfo;

    public Stock(int stockId, int itemId, int qty, String updateInfo) {
        this.stockId = stockId;
        this.itemId = itemId;
        this.qty = qty;
        this.updateInfo = updateInfo;
    }

    public Stock() {}

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
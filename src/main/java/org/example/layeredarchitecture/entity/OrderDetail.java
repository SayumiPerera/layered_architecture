package org.example.layeredarchitecture.entity;

import java.math.BigDecimal;

public class OrderDetail {

    private String oid;
    private String itemCode;
    private double unitPrice;
    private int qty;

    public OrderDetail() {}

    public OrderDetail(String oid, String itemCode, double unitPrice, int qty) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public OrderDetail(String orderId, String itemCode, int qty, BigDecimal unitPrice) {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
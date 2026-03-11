package org.example.layeredarchitecture.entity;

import java.time.LocalDate;

public class Order {

    private String orderId;     // oid (String "OID-001" format)
    private LocalDate orderDate;
    private String customerId;

    public Order() {}

    public Order(String orderId, LocalDate orderDate, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Object getCustomerName() {

        return null;
    }

    public Object getOrderTotal() {
        return null;
    }
}
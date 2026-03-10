package org.example.layeredarchitecture.entity;

import java.time.LocalDate;

public class Payment {

    private int paymentId;
    private int customerId;
    private String paymentMethod;
    private double totalAmount;
    private int qty;
    private LocalDate date;

    public Payment(int paymentId, int customerId, String paymentMethod, double totalAmount, int qty, LocalDate date) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.qty = qty;
        this.date = date;
    }

    public Payment() {}

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
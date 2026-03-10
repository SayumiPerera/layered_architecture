package org.example.layeredarchitecture.view.tdm;

import java.time.LocalDate;

public class PaymentTM implements Comparable<PaymentTM> {
    private int paymentId;
    private int customerId;
    private String paymentMethod;
    private double totalAmount;
    private int qty;
    private LocalDate date;

    public PaymentTM() {
    }

    public PaymentTM(int paymentId, int customerId, String paymentMethod, double totalAmount, int qty, LocalDate date) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.qty = qty;
        this.date = date;
    }

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

    @Override
    public String toString() {
        return "PaymentTM{" +
                "paymentId=" + paymentId +
                ", customerId=" + customerId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", qty=" + qty +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(PaymentTM o) {
        return Integer.compare(this.paymentId, o.paymentId);
    }
}
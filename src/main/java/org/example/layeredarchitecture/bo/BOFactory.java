package org.example.layeredarchitecture.bo;

import org.example.layeredarchitecture.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory instance;

    private BOFactory() {}  // private constructor for singleton

    public static BOFactory getInstance() {
        if (instance == null) {
            instance = new BOFactory();
        }
        return instance;
    }

    public enum BOType {
        CUSTOMER, EMPLOYEE, STOCK, SUPPLIER, ORDER, ITEM, PAYMENT
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
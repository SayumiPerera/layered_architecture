package org.example.layeredarchitecture.entity;

import java.util.List;

public class Supplier {

    private String supplierCompanyId;
    private String supplierCompanyName;
    private String address;
    private List<String> items;
    private String contact;

    public Supplier(String supplierCompanyId, String supplierCompanyName, String address, List<String> items, String contact) {
        this.supplierCompanyId = supplierCompanyId;
        this.supplierCompanyName = supplierCompanyName;
        this.address = address;
        this.items = items;
        this.contact = contact;
    }

    public Supplier() {}

    public String getSupplierCompanyId() {
        return supplierCompanyId;
    }

    public void setSupplierCompanyId(String supplierCompanyId) {
        this.supplierCompanyId = supplierCompanyId;
    }

    public String getSupplierCompanyName() {
        return supplierCompanyName;
    }

    public void setSupplierCompanyName(String supplierCompanyName) {
        this.supplierCompanyName = supplierCompanyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
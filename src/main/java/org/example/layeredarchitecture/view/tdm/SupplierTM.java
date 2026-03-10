package org.example.layeredarchitecture.view.tdm;

public class SupplierTM implements Comparable<SupplierTM> {
    private String supplierCompanyId;
    private String supplierCompanyName;
    private String address;
    private String items;   // stored as comma-separated string for easy TableView display
    private String contact;

    public SupplierTM() {
    }

    public SupplierTM(String supplierCompanyId, String supplierCompanyName, String address, String items, String contact) {
        this.supplierCompanyId = supplierCompanyId;
        this.supplierCompanyName = supplierCompanyName;
        this.address = address;
        this.items = items;
        this.contact = contact;
    }

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supplierCompanyId='" + supplierCompanyId + '\'' +
                ", supplierCompanyName='" + supplierCompanyName + '\'' +
                ", address='" + address + '\'' +
                ", items='" + items + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public int compareTo(SupplierTM o) {
        return supplierCompanyId.compareTo(o.getSupplierCompanyId());
    }

}
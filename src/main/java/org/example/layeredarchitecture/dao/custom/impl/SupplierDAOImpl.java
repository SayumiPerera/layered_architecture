package org.example.layeredarchitecture.dao.custom.impl;

import org.example.layeredarchitecture.dao.CRUDUtil;
import org.example.layeredarchitecture.dao.custom.SupplierDAO;
import org.example.layeredarchitecture.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();

        while (rst.next()) {
            String supplierCompanyId = rst.getString("supplierCompanyId");
            String supplierCompanyName = rst.getString("supplierCompanyName");
            String address = rst.getString("address");
            String itemsStr = rst.getString("items");
            List<String> items = (itemsStr == null || itemsStr.isEmpty())
                    ? new ArrayList<>()
                    : Arrays.asList(itemsStr.split(",\\s*"));
            String contact = rst.getString("contact");

            Supplier entity = new Supplier(supplierCompanyId, supplierCompanyName, address, items, contact);
            suppliers.add(entity);
        }
        return suppliers;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        String itemsStr = String.join(", ", entity.getItems());
        return CRUDUtil.execute("INSERT INTO Supplier (supplierCompanyId, supplierCompanyName, address, items, contact) VALUES (?,?,?,?,?)",
                entity.getSupplierCompanyId(),
                entity.getSupplierCompanyName(),
                entity.getAddress(),
                itemsStr,
                entity.getContact());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String itemsStr = String.join(", ", entity.getItems());
        return CRUDUtil.execute("UPDATE Supplier SET supplierCompanyName=?, address=?, items=?, contact=? WHERE supplierCompanyId=?",
                entity.getSupplierCompanyName(),
                entity.getAddress(),
                itemsStr,
                entity.getContact(),
                entity.getSupplierCompanyId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Supplier WHERE supplierCompanyId=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT supplierCompanyId FROM Supplier ORDER BY supplierCompanyId DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("supplierCompanyId");
            int newSupplierId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newSupplierId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Supplier WHERE supplierCompanyId=?", id);
        return rst.next();
    }

    @Override
    public Supplier find(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Supplier WHERE supplierCompanyId=?", id);
        if (rst.next()) {
            String itemsStr = rst.getString("items");
            List<String> items = (itemsStr == null || itemsStr.isEmpty())
                    ? new ArrayList<>()
                    : Arrays.asList(itemsStr.split(",\\s*"));

            return new Supplier(
                    rst.getString("supplierCompanyId"),
                    rst.getString("supplierCompanyName"),
                    rst.getString("address"),
                    items,
                    rst.getString("contact")
            );
        }
        return null;
    }
}
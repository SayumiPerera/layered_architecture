package org.example.layeredarchitecture.dao.custom.impl;

import org.example.layeredarchitecture.dao.CRUDUtil;
import org.example.layeredarchitecture.dao.custom.OrderDAO;
import org.example.layeredarchitecture.entity.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d",
                (Integer.parseInt(rst.getString("oid")
                        .replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order find(String id) throws SQLException, ClassNotFoundException {
        return find(null);
    }

    public boolean exits(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst= CRUDUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
        return rst.next();
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO `Orders` (oid, date, customerID,customerName,orderTotal) VALUES (?,?,?,?,?)",entity.getOrderId(),entity.getOrderDate(),entity.getCustomerId(),entity.getCustomerName(),entity.getOrderTotal());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists() throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void search(String id) throws SQLException, ClassNotFoundException {

    }

}

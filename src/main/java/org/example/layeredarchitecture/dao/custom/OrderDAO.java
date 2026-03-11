package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Customer;
import org.example.layeredarchitecture.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order> {
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Order entity) throws SQLException, ClassNotFoundException;
    public boolean update(Order entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean exists(String id) throws SQLException, ClassNotFoundException;
//    public Boolean find(String id) throws SQLException, ClassNotFoundException;
    public Order find(String id) throws SQLException, ClassNotFoundException;
}
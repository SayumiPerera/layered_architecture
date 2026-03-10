package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException;
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean exists(String id) throws SQLException, ClassNotFoundException;
    public OrderDetail find(String id) throws SQLException, ClassNotFoundException;
}
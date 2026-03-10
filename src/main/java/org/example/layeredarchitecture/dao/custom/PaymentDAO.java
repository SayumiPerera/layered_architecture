package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException;
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException;
    public boolean delete(int id) throws SQLException, ClassNotFoundException;
    public Payment find(int id) throws SQLException, ClassNotFoundException;
}
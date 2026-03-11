package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException;
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException;
    public boolean exists(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public Supplier find(String id) throws SQLException, ClassNotFoundException;
}
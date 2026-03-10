package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDAO extends CrudDAO<Stock> {
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException;
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException;
    public boolean delete(int id) throws SQLException, ClassNotFoundException;
    public Stock find(int id) throws SQLException, ClassNotFoundException;
}
package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Item entity) throws SQLException, ClassNotFoundException;
    public boolean update(Item entity) throws SQLException, ClassNotFoundException;
    public boolean delete(int id) throws SQLException, ClassNotFoundException;
    public Item find(int id) throws SQLException, ClassNotFoundException;
    public boolean exists(int id) throws SQLException, ClassNotFoundException;
    public boolean generateNewID() throws SQLException, ClassNotFoundException;
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException;



}
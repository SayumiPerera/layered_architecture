package org.example.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;

    public interface CrudDAO<T> extends SuperDAO{
        ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
        boolean save(T entity) throws SQLException, ClassNotFoundException;
        boolean update(T entity)throws SQLException, ClassNotFoundException;
        boolean delete(String id) throws SQLException, ClassNotFoundException;
        boolean exists()throws SQLException, ClassNotFoundException;
        void search(String id) throws SQLException, ClassNotFoundException;
        Object find(String id) throws SQLException, ClassNotFoundException;
    }



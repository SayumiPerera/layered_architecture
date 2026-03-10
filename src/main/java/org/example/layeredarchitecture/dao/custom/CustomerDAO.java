package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.dto.CustomerDTO;
import org.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

    public interface CustomerDAO extends CrudDAO<Customer> {
        public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;
        public boolean save(Customer entity) throws SQLException, ClassNotFoundException;
        public boolean update(Customer entity) throws SQLException, ClassNotFoundException;
        public boolean exists(String id) throws SQLException, ClassNotFoundException;
        public boolean delete(String id) throws SQLException, ClassNotFoundException;
        public String generateNewID() throws SQLException, ClassNotFoundException;
        public Customer find(String id) throws SQLException, ClassNotFoundException;

    }


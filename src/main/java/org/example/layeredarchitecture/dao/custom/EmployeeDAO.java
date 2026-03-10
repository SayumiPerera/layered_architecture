package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.CrudDAO;
import org.example.layeredarchitecture.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException;
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException;
    public boolean exists(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public Employee find(String id) throws SQLException, ClassNotFoundException;
}
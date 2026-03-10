package org.example.layeredarchitecture.dao.custom.impl;

import org.example.layeredarchitecture.dao.CRUDUtil;
import org.example.layeredarchitecture.dao.custom.EmployeeDAO;
import org.example.layeredarchitecture.entity.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> employees = new ArrayList<Employee>();

        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            double salary = rst.getDouble("salary");
            Employee entity = new Employee(id, name, address,salary);
            employees.add(entity);
        }
        return employees;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Employee (id, name, address) VALUES (?,?,?)", entity.getId(), entity.getName(), entity.getAddress());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE Employee SET name=?, address=? WHERE id=?", entity.getName(), entity.getAddress(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Employee WHERE id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT id FROM Employee ORDER BY id DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("id");
            int newEmployeeId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Employee WHERE id=?", id);
        return rst.next();
    }

    @Override
    public Employee find(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Employee WHERE id=?", id);
        if (rst.next()) {
            return new Employee(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getDouble("salary")
            );
        }
        return null;
    }

}
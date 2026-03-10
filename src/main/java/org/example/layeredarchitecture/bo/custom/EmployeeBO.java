package org.example.layeredarchitecture.bo.custom;

import org.example.layeredarchitecture.bo.SuperBO;
import org.example.layeredarchitecture.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;
    public boolean saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    public String generateEmployeeNewID() throws SQLException, ClassNotFoundException;
    public EmployeeDTO findEmployee(String id) throws SQLException, ClassNotFoundException;

}
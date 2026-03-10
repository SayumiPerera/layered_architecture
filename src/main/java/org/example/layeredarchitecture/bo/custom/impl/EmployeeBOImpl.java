package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.EmployeeBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.EmployeeDAO;
import org.example.layeredarchitecture.dto.EmployeeDTO;
import org.example.layeredarchitecture.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDTO(employee.getId(), employee.getName(), employee.getAddress(),employee.getSalary()));
        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(),employeeDTO.getSalary()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(),employeeDTO.getSalary()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exists(id);
    }

    @Override
    public String generateEmployeeNewID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    @Override
    public EmployeeDTO findEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.find(id);
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getAddress(),employee.getSalary());
    }

}

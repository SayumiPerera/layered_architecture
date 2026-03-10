package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.SupplierBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.SupplierDAO;
import org.example.layeredarchitecture.dto.SupplierDTO;
import org.example.layeredarchitecture.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDto = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDto.add(new SupplierDTO(supplier.getSupplierCompanyId(), supplier.getSupplierCompanyName(), supplier.getAddress(),supplier.getItems(),supplier.getContact()));
        }
        return supplierDto;
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierCompanyId(), supplierDTO.getSupplierCompanyName(), supplierDTO.getAddress(),supplierDTO.getItems(),supplierDTO.getContact()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierCompanyId(), supplier.getSupplierCompanyName(), supplier.getAddress(),supplier.getItems(),supplier.getContact()));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        //business logic
        return supplierDAO.exists(id);
    }

    @Override
    public String generateSupplierNewID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public SupplierDTO findSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.find(id);
        return new SupplierDTO(supplier.getSupplierCompanyId(), supplier.getSupplierCompanyName(), supplier.getAddress(),supplier.getItems(),supplier.getContact());
    }

}
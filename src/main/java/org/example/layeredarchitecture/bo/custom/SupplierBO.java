package org.example.layeredarchitecture.bo.custom;

import org.example.layeredarchitecture.bo.SuperBO;
import org.example.layeredarchitecture.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    String generateSupplierNewID() throws SQLException, ClassNotFoundException;
    SupplierDTO findSupplier(String id) throws SQLException, ClassNotFoundException;
}
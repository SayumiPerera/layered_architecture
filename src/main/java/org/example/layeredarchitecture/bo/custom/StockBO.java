package org.example.layeredarchitecture.bo.custom;

import org.example.layeredarchitecture.bo.SuperBO;
import org.example.layeredarchitecture.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException;
    boolean saveStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException;
    boolean updateStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException;
    boolean deleteStock(int id) throws SQLException, ClassNotFoundException;
    StockDTO findStock(int id) throws SQLException, ClassNotFoundException;
}
package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.StockBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.StockDAO;
import org.example.layeredarchitecture.dto.StockDTO;
import org.example.layeredarchitecture.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.STOCK);

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stocks = stockDAO.getAll();
        ArrayList<StockDTO> stockDtos = new ArrayList<>();

        for (Stock stock : stocks) {
            stockDtos.add(new StockDTO(stock.getStockId(), stock.getItemId(), stock.getQty(), stock.getUpdateInfo()));
        }
        return stockDtos;
    }

    @Override
    public boolean saveStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(stockDTO.getStockId(), stockDTO.getItemId(), stockDTO.getQty(), stockDTO.getUpdateInfo()));
    }

    @Override
    public boolean updateStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stockDTO.getStockId(), stockDTO.getItemId(), stockDTO.getQty(), stockDTO.getUpdateInfo()));
    }

    @Override
    public boolean deleteStock(int id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public StockDTO findStock(int id) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.find(id);
        return (stock != null) ? new StockDTO(stock.getStockId(), stock.getItemId(), stock.getQty(), stock.getUpdateInfo()) : null;
    }
}
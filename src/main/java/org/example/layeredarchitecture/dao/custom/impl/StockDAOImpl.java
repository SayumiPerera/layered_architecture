package org.example.layeredarchitecture.dao.custom.impl;

import org.example.layeredarchitecture.dao.CRUDUtil;
import org.example.layeredarchitecture.dao.custom.StockDAO;
import org.example.layeredarchitecture.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {

    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM stock");
        ArrayList<Stock> stocks = new ArrayList<>();

        while (rst.next()) {
            Stock entity = new Stock(
                    rst.getInt("stock_id"),
                    rst.getInt("item_id"),
                    rst.getInt("qty"),
                    rst.getString("update_info")
            );
            stocks.add(entity);
        }
        return stocks;
    }

    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO stock (stock_id, item_id, qty, update_info) VALUES (?,?,?,?)",
                entity.getStockId(), entity.getItemId(), entity.getQty(), entity.getUpdateInfo());
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE stock SET item_id = ?, qty = ?, update_info = ? WHERE stock_id = ?",
                entity.getItemId(), entity.getQty(), entity.getUpdateInfo(), entity.getStockId());
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM stock WHERE stock_id = ?", id);
    }

    @Override
    public Stock find(int id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM stock WHERE stock_id = ?", id);
        if (rst.next()) {
            return new Stock(
                    rst.getInt("stock_id"),
                    rst.getInt("item_id"),
                    rst.getInt("qty"),
                    rst.getString("update_info")
            );
        }
        return null;
    }
}
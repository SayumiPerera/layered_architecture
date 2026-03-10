package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.ItemBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.ItemDAO;
import org.example.layeredarchitecture.dto.ItemDTO;
import org.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items= itemDAO.getAll();
        ArrayList<ItemDTO> list = new ArrayList<>();

        for (Item item : items) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return list;
    }

    @Override
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exists(Integer.parseInt(id));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateItemNewID() throws SQLException, ClassNotFoundException {
        return String.valueOf(itemDAO.generateNewID());
    }

    @Override
    public ItemDTO findItem(String id) throws SQLException, ClassNotFoundException {
        Item item= itemDAO.find(Integer.parseInt(id));
        return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

}

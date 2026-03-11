package org.example.layeredarchitecture.bo.custom;

import org.example.layeredarchitecture.bo.SuperBO;
import org.example.layeredarchitecture.dto.CustomerDTO;
import org.example.layeredarchitecture.dto.ItemDTO;
import org.example.layeredarchitecture.dto.OrderDTO;
import org.example.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public boolean existItem(String id) throws SQLException, ClassNotFoundException;
    boolean existItem(int id) throws SQLException, ClassNotFoundException;
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String id) throws SQLException, ClassNotFoundException;
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, Double unitPrice, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}

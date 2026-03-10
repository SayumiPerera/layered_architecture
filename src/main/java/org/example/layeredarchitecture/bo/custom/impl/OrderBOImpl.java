package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.OrderBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.CustomerDAO;
import org.example.layeredarchitecture.dao.custom.ItemDAO;
import org.example.layeredarchitecture.dao.custom.OrderDAO;
import org.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import org.example.layeredarchitecture.db.DBConnection;
import org.example.layeredarchitecture.dto.CustomerDTO;
import org.example.layeredarchitecture.dto.ItemDTO;
import org.example.layeredarchitecture.dto.OrderDetailDTO;
import org.example.layeredarchitecture.entity.Customer;
import org.example.layeredarchitecture.entity.Item;
import org.example.layeredarchitecture.entity.Order;
import org.example.layeredarchitecture.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.ITEM);
    OrderDAO  orderDAO = (OrderDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.ORDER_DETAIL);

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exists(id);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existItem(int id) throws SQLException, ClassNotFoundException {
        return itemDAO.exists(id);
    }

    @Override
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDAO.find(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }

    @Override
    public ItemDTO findItem(String id) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.find(Integer.parseInt(id));
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers= customerDAO.getAll();
        ArrayList<CustomerDTO> customerDto=new ArrayList<>();
        for (Customer customer : customers) {
            customerDto.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDto;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items= itemDAO.getAll();
        ArrayList<ItemDTO> list = new ArrayList<>();
        for (Item item : items) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return list;
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId,Double unitPrice, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection= DBConnection.getDbConnection().getConnection();
            boolean b1=orderDAO.exists(orderId);
            /*if order id already exist*/
            if (b1) {
                //Alert
            }

            connection.setAutoCommit(false);

            boolean b2 = orderDAO.save(new Order(orderId,orderDate,customerId));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            for (OrderDetailDTO detail : orderDetails) {
                boolean b3=orderDetailDAO.save(new OrderDetail(detail.getOrderId(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));

                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                boolean b4=itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));

                if (!b4) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}

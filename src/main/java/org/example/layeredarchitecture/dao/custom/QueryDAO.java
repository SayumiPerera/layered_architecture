package org.example.layeredarchitecture.dao.custom;

import org.example.layeredarchitecture.dao.SuperDAO;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    void  getAllOrdersByCustomerName() throws SQLException, ClassNotFoundException;
}

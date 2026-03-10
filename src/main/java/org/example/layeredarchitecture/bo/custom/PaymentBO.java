package org.example.layeredarchitecture.bo.custom;

import org.example.layeredarchitecture.bo.SuperBO;
import org.example.layeredarchitecture.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
    boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    boolean updatePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    boolean deletePayment(int id) throws SQLException, ClassNotFoundException;
    PaymentDTO findPayment(int id) throws SQLException, ClassNotFoundException;
}
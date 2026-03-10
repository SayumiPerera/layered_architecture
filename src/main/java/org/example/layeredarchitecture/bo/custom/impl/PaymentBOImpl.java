package org.example.layeredarchitecture.bo.custom.impl;

import org.example.layeredarchitecture.bo.custom.PaymentBO;
import org.example.layeredarchitecture.dao.DAOFactory;
import org.example.layeredarchitecture.dao.custom.PaymentDAO;
import org.example.layeredarchitecture.dto.PaymentDTO;
import org.example.layeredarchitecture.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDtos = new ArrayList<>();

        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getCustomerId(),
                    payment.getPaymentMethod(),
                    payment.getTotalAmount(),
                    payment.getQty(),
                    payment.getDate()
            ));
        }
        return paymentDtos;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getCustomerId(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getTotalAmount(),
                paymentDTO.getQty(),
                paymentDTO.getDate()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getCustomerId(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getTotalAmount(),
                paymentDTO.getQty(),
                paymentDTO.getDate()
        ));
    }

    @Override
    public boolean deletePayment(int id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO findPayment(int id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.find(id);
        return (payment != null) ? new PaymentDTO(
                payment.getPaymentId(),
                payment.getCustomerId(),
                payment.getPaymentMethod(),
                payment.getTotalAmount(),
                payment.getQty(),
                payment.getDate()
        ) : null;
    }
}
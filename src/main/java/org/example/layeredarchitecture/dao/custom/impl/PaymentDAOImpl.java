package org.example.layeredarchitecture.dao.custom.impl;

import org.example.layeredarchitecture.dao.CRUDUtil;
import org.example.layeredarchitecture.dao.custom.PaymentDAO;
import org.example.layeredarchitecture.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> payments = new ArrayList<>();

        while (rst.next()) {
            Payment entity = new Payment(
                    rst.getInt("payment_id"),
                    rst.getInt("customer_id"),
                    rst.getString("payment_method"),
                    rst.getDouble("total_amount"),
                    rst.getInt("qty"),
                    rst.getDate("date").toLocalDate()
            );
            payments.add(entity);
        }
        return payments;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO payment (customer_id, payment_method, total_amount, qty, date) VALUES (?,?,?,?,?)",
                entity.getCustomerId(),
                entity.getPaymentMethod(),
                entity.getTotalAmount(),
                entity.getQty(),
                java.sql.Date.valueOf(entity.getDate())
        );
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE payment SET customer_id = ?, payment_method = ?, total_amount = ?, qty = ?, date = ? WHERE payment_id = ?",
                entity.getCustomerId(),
                entity.getPaymentMethod(),
                entity.getTotalAmount(),
                entity.getQty(),
                java.sql.Date.valueOf(entity.getDate()),
                entity.getPaymentId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists() throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void search(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean find(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM payment WHERE payment_id = ?", id);
    }

    @Override
    public Payment find(int id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM payment WHERE payment_id = ?", id);
        if (rst.next()) {
            return new Payment(
                    rst.getInt("payment_id"),
                    rst.getInt("customer_id"),
                    rst.getString("payment_method"),
                    rst.getDouble("total_amount"),
                    rst.getInt("qty"),
                    rst.getDate("date").toLocalDate()
            );
        }
        return null;
    }
}
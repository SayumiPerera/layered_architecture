package org.example.layeredarchitecture.dao;

import org.example.layeredarchitecture.dao.custom.impl.*;

public class DAOFactory {

        private static DAOFactory daoFactory;
        private  DAOFactory(){
        }
        public static DAOFactory getInstance(){
            return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
        }

        public enum DAOType{
            CUSTOMER,EMPLOYEE,STOCK,SUPPLIER,ORDER,ORDER_DETAIL,ITEM,PAYMENT
        }

        public SuperDAO getDAOType(DAOType daoType){
            switch(daoType){
                case CUSTOMER:
                    return new CustomerDAOImpl();
                case EMPLOYEE:
                    return new EmployeeDAOImpl();
                case STOCK:
                    return new StockDAOImpl();
                case SUPPLIER:
                    return new SupplierDAOImpl();
                case ORDER:
                    return new OrderDAOImpl();
                case ITEM:
                    return new ItemDAOImpl();
                case PAYMENT:
                    return new PaymentDAOImpl();
                default:
                    return null;
            }
        }


}

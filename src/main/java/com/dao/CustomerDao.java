package com.dao;

import com.exceptions.UserException;
import com.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Customer> {

    private static final String CREATE_QUERY = "insert into customers(id,name,surname,phone_number, email,services,wallet,isActive,created,updated) values (?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from customers where phone = ?";
    private static final String UPDATE_QUERY = "UPDATE customers SET item=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE  FROM customers WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from customers";
    private static Logger logger = LogManager.getLogger(UserDao.class);

    private CustomerDao customerDao;

    @Override
    public Customer create(Customer customer) {
        logger.debug("Start customer creating");
        if (customer == null) {
            logger.error("customer not found");
            throw new UserException("customer is not found");
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY);) {
            pst.setInt(1, customer.getId());
            pst.setString(2, customer.getName());
            pst.setString(3, customer.getSurname());
            pst.setString(4, customer.getPhone_number());
            pst.setString(5, customer.getEmail());
            // pst.setString(6, customer.getServices());
            // pst.setString(7, customer.getWallet());
            pst.setBoolean(8, customer.isActive());

            pst.setTimestamp(9, customer.convertToTimestamp(customer.getCreated()));
            pst.setTimestamp(10, customer.convertToTimestamp(customer.getUpdated()));

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Created more than one record!!");

        } catch (Exception ex) {
            logger.debug("Problem with creating customer: " + ex.getMessage());
        }

        logger.debug("Customer created");

        System.out.println(customer.toString());
        return customer;
    }

    @Override
    public Customer findByField(String value) {
        Customer customer = new Customer();

        logger.debug("Start customer searching....");


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {

            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();

            customer.setName(resultSet.getString("name"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setPhone_number(resultSet.getString("phone_number"));
            customer.setEmail(resultSet.getString("email"));
            customer.setWallet(resultSet.getObject("wallet", Wallet.class));
            //customer.setServices(resultSet.getObject("subscriptions", ArrayList<Subscription.class>);

            customer.setActive(resultSet.getBoolean("isActive"));
            customer.setCreated(LocalDateTime.parse(resultSet.getString("created")));
            customer.setUpdated(LocalDateTime.parse(resultSet.getString("updated")));

            if (customer == null) {
                throw new UserException("customer not found");

            }

        } catch (Exception ex) {
            logger.debug("Problem with searching customer: " + ex.getMessage());
        }

        logger.debug("Customer searched");


        return customer;
    }

    @Override
    public Customer update(Customer item) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }


}

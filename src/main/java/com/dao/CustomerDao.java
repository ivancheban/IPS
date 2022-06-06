package com.dao;

import com.exceptions.UserException;
import com.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Customer> {

    private static final String CREATE_QUERY = "insert into customers(name,surname, phone,email) values (?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from customers where phone = ?";
    private static final String UPDATE_QUERY = "UPDATE customers SET item=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE  FROM customers WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from customers";
    private static Logger logger = LogManager.getLogger(CustomerDao.class);

    private CustomerDao customerDao;

    private Connection con;

    public CustomerDao(Connection con) {
        try {
            this.con  = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CustomerDao() {
    }

    @Override
    public Customer create(Customer customer) {
        logger.debug("Start customer creating");
        if (customer == null) {
            logger.error("customer not found");
            throw new UserException("customer is not found");
        }


        try (
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY);) {

            pst.setString(1, customer.getName());
            pst.setString(2, customer.getSurname());
            pst.setString(3, customer.getPhone());
            pst.setString(4, customer.getEmail());

            // pst.setString(6, customer.getServices());
            // pst.setString(7, customer.getWallet());
//            pst.setBoolean(8, customer.isActive());
//
//            pst.setTimestamp(9, customer.convertToTimestamp(customer.getCreated()));
//            pst.setTimestamp(10, customer.convertToTimestamp(customer.getUpdated()));

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
            customer.setPhone(resultSet.getString("phone_number"));
            customer.setEmail(resultSet.getString("email"));
           // customer.setWallet(resultSet.getObject("wallet", Wallet.class));
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

        Customer customer = new Customer();


        logger.debug("Start customer updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {
            pst.setInt(1, customer.getId());

            customer.setName(item.getName());
            customer.setSurname(item.getSurname());
            customer.setPhone(item.getPhone());
            customer.setEmail(item.getEmail());
            customer.setActive(item.isActive());
            customer.setCreated(item.getCreated());
            customer.setUpdated(item.getUpdated());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Updated more than one record!!");


        } catch (Exception ex) {

            logger.debug("Problem with updating customer: " + ex.getMessage());
        }

        logger.debug("Customer updated");
        return customer;
    }

    @Override
    public boolean delete(int id) {
        boolean status_boolean = false;
        logger.debug("Start customer deleting....");

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_QUERY);) {
            pst.setInt(1, id);

            int status = pst.executeUpdate();
            if (status == 1) {
                status_boolean = true;
            }
            if (status != 1) throw new UserException("Deleted more than one record!!");
            con.close();
        } catch (Exception ex) {
            logger.debug("Problem with deleting customer: " + ex.getMessage());
        }

        logger.debug("customer deleted");
        return status_boolean;
    }

    @Override
    public List<Customer> findAll() {

        logger.debug("Start  searching all customers....");
        List<Customer> customersList = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
            customersList = new ArrayList<>();
            Customer customer= null;
            while (result.next()) {
                customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setSurname(result.getString("surname"));
                customer.setPhone(result.getString("phone"));
                customer.setEmail(result.getString("password"));
                customer.setActive(result.getBoolean("isActive"));
                customer.setCreated(result.getTimestamp("created").toLocalDateTime());
                customer.setUpdated(result.getTimestamp("updated").toLocalDateTime());

                customersList.add(customer);

            }


        } catch (Exception ex) {
            logger.debug("Problem with searching all customers: " + ex.getMessage());
        }

        logger.debug("All Customers searched");
        System.out.println(customersList);

        return customersList;
    }


}

package com.dao;

import com.exceptions.TariffException;
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

    private static final String CREATE_QUERY = "insert into customers(name,surname, phone,email,balance) values (?,?,?,?,?)";
    private static final String FIND_BY_PHONE_NUMBER = "select * from customers where phone = ?";
    private static final String FIND_BY_ID = "select * from customers where id = ?";
    private static final String UPDATE_QUERY = "UPDATE customers SET name=?,surname=?,phone=?,email=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE  FROM customers WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from customers";
    private static final String UPDATE_BALANCE = "UPDATE customers SET balance = ? WHERE id =?";
    private static final String ADD_TARIFF_QUERY = "insert into customers_tariffs(customers_id,tariffs_id ) values (?,?)";
    private static final String FIND_ALL_SERVICE = "select *from subscriptions where customers_id=?";
    private static final String SELECT_ALL_TARIFFS_CUSTOMER = "select * from customers_tariffs where customers_id=?";
    private  static  final String DELETE_TARIFF_OF_CUSTOMER_QUERY ="DELETE FROM customers_tariffs where customers_id = ? and tariffs_id = ?";
    private static Logger logger = LogManager.getLogger(CustomerDao.class);

    private CustomerDao customerDao;
    private TariffDao tariffDao;

    private Connection con;

    public CustomerDao(Connection con) {
        try {
            this.con = DataSource.getConnection();
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
            pst.setInt(5, customer.getBalance());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Created more than one record!!");

        } catch (Exception ex) {
            logger.debug("Problem with creating customer: " + ex.getMessage());
        }
        logger.debug("Customer created");
        return customer;
    }

    @Override
    public Customer findByField(String value) {
        Customer customer = new Customer();
        logger.debug("Start customer searching....");

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_PHONE_NUMBER);) {
            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setActive(resultSet.getBoolean("isActive"));
                customer.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                customer.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                customer.setBalance(resultSet.getInt("balance"));
                if (customer == null) {
                    throw new UserException("customer not found");
                }
            }
        } catch (Exception ex) {
            logger.debug("Problem with searching customer: " + ex.getMessage());
        }
        logger.debug("Customer searched");
        return customer;
    }

    public Customer findByID(int id) {
        Customer customer = new Customer();
        logger.debug("Start customer searching....");

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_ID);) {
            pst.setInt(1, id);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setActive(resultSet.getBoolean("isActive"));
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();
                customer.setBalance(resultSet.getInt("balance"));
                customer.setTariffs(getAllTariffs(customer.getId()));
                if (customer == null) {
                    throw new UserException("customer not found");
                }
            }
        } catch (Exception ex) {
            logger.debug("Problem with searching customer: " + ex.getMessage());
        }
        logger.debug("Customer searched");
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        logger.debug("Start customer updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {
            pst.setString(1, customer.getName());
            pst.setString(2,customer.getSurname());
            pst.setString(3,customer.getPhone());
            pst.setString(4, customer.getEmail());
            pst.setInt(5,customer.getId());

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
            Customer customer = null;
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
                customer.setBalance(result.getInt("balance"));
                customersList.add(customer);
            }
        } catch (Exception ex) {
            logger.debug("Problem with searching all customers: " + ex.getMessage());
        }
        logger.debug("All Customers searched");
        System.out.println(customersList);
        return customersList;
    }

    public boolean addBalance(int customer_id, int money) {
        boolean status_replenish = false;
        logger.debug("Start replenish amount....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_BALANCE);) {

            Customer customer = findByID(customer_id);
            int newBalance = customer.getBalance() + money;
            pst.setInt(1, newBalance);
            pst.setInt(2, customer_id);

            int status = pst.executeUpdate();
            System.out.println("status ==> " + status);
            if (status == 1) {
                status_replenish = true;
            }
            if (status != 1) throw new SQLException();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug("Problem with replenish amount: " + ex.getMessage());
        }

        logger.debug("replenished amount");
        return status_replenish;
    }

    public boolean withdrawBalance(int customer_id, int money) {
        boolean status_replenish = false;
        logger.debug("Start replenish amount....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_BALANCE);) {

            Customer customer = findByID(customer_id);
            int newBalance = customer.getBalance() - money;
            pst.setInt(1, newBalance);
            pst.setInt(2, customer_id);

            int status = pst.executeUpdate();
            System.out.println("status ==> " + status);
            if (status == 1) {
                status_replenish = true;
            }
            if (status != 1) throw new SQLException();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug("Problem with replenish amount: " + ex.getMessage());
        }

        logger.debug("replenished amount");
        return status_replenish;
    }
    public void addTariffCustomer(int customersId, int tariffId)  {
        logger.debug("Start tariff creating");
        if (tariffId == 0) {
            logger.error("Illegal Argument!!!");
            throw new IllegalArgumentException();
        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ADD_TARIFF_QUERY);) {
            pst.setInt(1, customersId);
            pst.setInt(2, tariffId);
            int status = pst.executeUpdate();
            if (status != 1) throw new TariffException("Created more than one record!!");
        } catch ( SQLException | TariffException ex) {
            logger.debug("Problem with adding tariff to customer: " + ex.getMessage());

        }
        logger.debug("customer payment tariff of service ");
    }
    public void deleteTariffCustomer(int customersId, int tariffId)  {
        logger.debug("Start tariff delete");
        if (tariffId == 0) {
            logger.error("Illegal Argument!!!");
            throw new IllegalArgumentException();
        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_TARIFF_OF_CUSTOMER_QUERY);) {
            pst.setInt(1, customersId);
            pst.setInt(2, tariffId);
            int status = pst.executeUpdate();
            if (status != 1) throw new TariffException("Created more than one record!!");
        } catch ( SQLException | TariffException ex) {
            logger.debug("Problem with delete tariff to customer: " + ex.getMessage());
        }
        logger.debug(" delete tariff of customer ");
    }
    public List<Tariff> getAllTariffs(int customer) {
        logger.debug("Start  searching all tariffs ...");
        List<Tariff> tariffs = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_TARIFFS_CUSTOMER);) {
            pst.setInt(1, customer);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("tariff_id");
                tariffs.add(tariffDao.findById(id));
            }
        } catch (Exception ex) {
            logger.debug("Problem with getting all tariffs of customer: " + ex.getMessage());
        }
        logger.debug("All tariffs off customer found");
        return tariffs;
    }

}

package com.dao;

import com.exceptions.SubscriptionException;
import com.exceptions.TariffException;
import com.exceptions.UserException;
import com.model.ServiceType;
import com.model.Subscription;
import com.model.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDao implements Dao<Subscription> {


    private static final String CREATE_QUERY = "insert into subscriptions(name,days_amount,isActive,created, updated) values (?,?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from subscriptions where name = ?";
    private static final String UPDATE_QUERY = "UPDATE subscriptions SET item=? WHERE name=?";
    private static final String DELETE_QUERY = "DELETE  FROM subscriptions WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from subscriptions";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from subscriptions limit ?, ?";
    private static Logger logger = LogManager.getLogger(SubscriptionDao.class);

    private int noOfRecords;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Subscription create(Subscription subscription) throws SubscriptionException {

        logger.debug("Start subscription creating");
        if (subscription == null) {
            logger.error("subscription not found");
            throw new SubscriptionException("subscription is not found");
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);) {

            pst.setString(1, subscription.getName());
            pst.setInt(2, subscription.getDays_amount());
            pst.setBoolean(3, subscription.isActive());
            pst.setTimestamp(4, subscription.convertToTimestamp(subscription.getCreated()));
            pst.setTimestamp(5, subscription.convertToTimestamp(subscription.getUpdated()));

            int status = pst.executeUpdate();

            if (status != 1) throw new SubscriptionException("Created more than one record!!");
            ResultSet keys = pst.getGeneratedKeys();
            if(keys.next()){
                int id = keys.getInt(1);
                subscription.setId(id);
                System.out.println(subscription.getId());
            }

        } catch (Exception ex) {
            logger.debug("Problem with creating subscription: " + ex.getMessage());
            throw new SubscriptionException(ex.getMessage(), ex);
        }

        logger.debug("subscription created");


        return subscription;
    }


    @Override
    public Subscription findByField(String value) {
        Subscription subscription = new Subscription();

        logger.debug("Start subscription searching....");


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {

            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();


            subscription.setName(resultSet.getString("name"));
            subscription.setDays_amount(resultSet.getInt("days_amount"));
            subscription.setActive(resultSet.getBoolean("isActive"));
            subscription.setCreated(LocalDateTime.parse(resultSet.getString("created")));
            subscription.setUpdated(LocalDateTime.parse(resultSet.getString("updated")));


        } catch (Exception ex) {
            logger.debug("Problem with searching subscription: " + ex.getMessage());
        }

        logger.debug("subscription searched");

        System.out.println(subscription.toString());
        return subscription;
    }

    @Override
    public Subscription update(Subscription sub) {
        Subscription subscription = new Subscription();

        logger.debug("Start  subscription updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {
            pst.setInt(1, subscription.getId());

            subscription.setName(sub.getName());

            subscription.setDays_amount(sub.getDays_amount());
            subscription.setActive(sub.isActive());
            subscription.setCreated(sub.getCreated());
            subscription.setUpdated(sub.getUpdated());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Updated more than one record!!");


        } catch (Exception ex) {

            logger.debug("Problem with updating  subscription: " + ex.getMessage());
        }

        logger.debug(" subscription updated");
        return subscription;
    }

    @Override
    public boolean delete(int id) {
        boolean status_boolean = false;
        logger.debug("Start subscriptions deleting....");

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
            logger.debug("Problem with deleting subscriptions: " + ex.getMessage());
        }

        logger.debug("Subscriptions deleted");
        return status_boolean;
    }

    @Override
    public List<Subscription> findAll() {
        logger.debug("Start  searching all subscriptions...");
        List<Subscription> subscriptionsList = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
            subscriptionsList = new ArrayList<>();
            Subscription subscription = null;
            while (result.next()) {
                subscription = new Subscription();
                subscription.setId(result.getInt("id"));
                subscription.setName(result.getString("name"));
                subscription.setId(result.getInt("days_amount"));
                subscription.setActive(result.getBoolean("isActive"));
                subscription.setCreated(result.getTimestamp("created").toLocalDateTime());
                subscription.setUpdated(result.getTimestamp("updated").toLocalDateTime());

                subscriptionsList.add(subscription);

            }


        } catch (Exception ex) {
            logger.debug("Problem with searching all subscriptions: " + ex.getMessage());
        }

        logger.debug("All subscriptions searched");
        System.out.println(subscriptionsList);

        return subscriptionsList;
    }

    public List<Subscription> getAll(int offset, int noOfRecords) {

        Connection con = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            con = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Subscription> subscriptionList = new ArrayList<>();
        Subscription subscription = null;
        try {
            pstmt = con.prepareStatement(SQL_CALC_FOUND_ROWS);
            statement = con.createStatement();
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);

            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int days_amount = resultSet.getInt("days_amount");
                boolean isActive = resultSet.getBoolean("isActive");
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();

               subscription = new Subscription(id, name, days_amount, isActive, created, updated);
                subscriptionList.add(subscription);
            }
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                if (resultSet != null)
                    resultSet.close();
                if (pstmt != null)
                    pstmt.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionList;

    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}

package com.dao;

import com.exceptions.UserException;
import com.model.ServiceType;
import com.model.Subscription;
import com.model.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionDao implements Dao<Subscription>{


    private static final String CREATE_QUERY = "insert into subscriptions(id,name,tariffs,days,isActive,created, updated) values (?,?,?,?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from subscriptions where name = ?";
    private static final String UPDATE_QUERY = "UPDATE subscriptions SET item=? WHERE name=?";
    private static final String DELETE_QUERY = "DELETE  FROM subscriptions WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from subscriptions";
    private static Logger logger = LogManager.getLogger(SubscriptionDao.class);

    @Override
    public Subscription create(Subscription subscription) {

            logger.debug("Start subscription creating");
            if (subscription == null) {
                logger.error("subscription not found");
                throw new UserException("subscription is not found");
            }


            try (Connection con = DataSource.getConnection();
                 PreparedStatement pst = con.prepareStatement(CREATE_QUERY);) {
                pst.setInt(1, subscription.getId());
                pst.setString(2, subscription.getName());
                pst.setString(3, String.valueOf(subscription.getTariffs()));
                pst.setInt(4, subscription.getDays());
                pst.setBoolean(5, subscription.isActive());
                pst.setTimestamp(6, subscription.convertToTimestamp(subscription.getCreated()));
                pst.setTimestamp(7, subscription.convertToTimestamp(subscription.getUpdated()));

                int status = pst.executeUpdate();
                if (status != 1) throw new UserException("Created more than one record!!");

            } catch (Exception ex) {
                logger.debug("Problem with creating subscription: " + ex.getMessage());
            }

            logger.debug("subscription created");

            System.out.println(subscription.toString());
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
           // subscription.setTariffs(resultSet.getString(String.valueOf("tariffs"));
            subscription.setDays(resultSet.getInt("pricePerDay"));
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
            subscription.setTariffs(sub.getTariffs());
            subscription.setDays(sub.getDays());
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
        return false;
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }
}

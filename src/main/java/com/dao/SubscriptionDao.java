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
    private static final String FIND_ALL_BY_SERVICE = "select *from tariffs where service_type=?";
    private static final String CREATE_QUERY = "insert into subscriptions(name,isActive,created, updated) values (?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from subscriptions where name = ?";
    private static final String FIND_BY_ID_QUERY = "select * from subscriptions where id = ?";
    private static final String UPDATE_QUERY = "UPDATE subscriptions SET name= ?, isActive = ?, updated = now() WHERE name = ?";
    private static final String UPDATE_QUERY_ID = "UPDATE subscriptions SET name=?,isActive = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE  FROM subscriptions WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from subscriptions";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from subscriptions limit ?, ?";
    private static final String SELECT_ALL_TARIFFS_QUERY = "select * from subscriptions_tariffs where subscription_id=?";
    private static final String ADD_TARIFF_QUERY = "insert into subscriptions_tariffs(subscription_id,tariff_id ) values (?,?)";
    private static final String DELETE_TARIFF_QUERY = "DELETE FROM subscriptions_tariffs WHERE subscription_id = ? AND tariff_id = ?;";
    private TariffDao tariffDao = new TariffDao();
    private static Logger logger = LogManager.getLogger(SubscriptionDao.class);

    private int noOfRecords;


    public List<Tariff> getAllTariffs(int subs) {
        logger.debug("Start  searching all tariffs ...");
        List<Tariff> tariffs = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_TARIFFS_QUERY);) {
            pst.setInt(1, subs);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("tariff_id");
                tariffs.add(tariffDao.findById(id));
            }
        } catch (Exception ex) {
            logger.debug("Problem with getting all tariffs for subscription: " + ex.getMessage());
        }
        logger.debug("All tariffs for subscription found");
        return tariffs;
    }


    public void addTariff(int sub_id, int tariff_id) throws SubscriptionException {
        logger.debug("Start subscription creating");
        if (tariff_id == 0) {
            logger.error("Illegal Argument!!!");
            throw new IllegalArgumentException();
        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ADD_TARIFF_QUERY);) {
            pst.setInt(1, sub_id);
            pst.setInt(2, tariff_id);
            int status = pst.executeUpdate();
            if (status != 1) throw new SubscriptionException("Created more than one record!!");
        } catch (Exception ex) {
            logger.debug("Problem with adding tariff to subscription: " + ex.getMessage());
            throw new SubscriptionException(ex.getMessage(), ex);
        }
        logger.debug("subscription tariff created");
    }
    public void deleteTariff(int sub_id, int tariff_id) throws SubscriptionException {
        logger.debug("Start subscription tariff delete");
        if (tariff_id == 0) {
            logger.error("Illegal Argument!!!");
            throw new IllegalArgumentException();
        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_TARIFF_QUERY);) {
            pst.setInt(1, sub_id);
            pst.setInt(2, tariff_id);
            int status = pst.executeUpdate();
            if (status != 1) throw new SubscriptionException("Deleted more than one record!!");
        } catch (Exception ex) {
            logger.debug("Problem with delete tariff to subscription: " + ex.getMessage());
            throw new SubscriptionException(ex.getMessage(), ex);
        }
        logger.debug("subscription tariff deleted");
    }

    @Override
    public Subscription create(Subscription subscription)  {
        logger.debug("Start subscription creating");
        if (subscription == null) {
            logger.error("subscription not found");

        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);) {

            pst.setString(1, subscription.getName());
            pst.setBoolean(2, subscription.isActive());
            pst.setTimestamp(3, subscription.convertToTimestamp(subscription.getCreated()));
            pst.setTimestamp(4, subscription.convertToTimestamp(subscription.getUpdated()));

            int status = pst.executeUpdate();

            if (status != 1) throw new SQLException("Service is not created");
            ResultSet keys = pst.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                subscription.setId(id);
            }
        } catch (SQLException ex) {
            logger.debug("Problem with creating subscription: " + ex.getMessage());
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
            subscription.setId(resultSet.getInt("id"));
            subscription.setName(resultSet.getString("name"));
            subscription.setActive(resultSet.getBoolean("isActive"));
            subscription.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
            subscription.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
            subscription.setTariffs(getAllTariffs(subscription.getId()));
        } catch (Exception ex) {
            logger.debug("Problem with searching subscription: " + ex.getMessage());
        }
        logger.debug("subscription searched");
        System.out.println(subscription.toString());
        return subscription;
    }

    public Subscription findById(int id) {
        Subscription subscription = new Subscription();
        logger.debug("Start subscription searching....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_ID_QUERY);) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            subscription.setId(resultSet.getInt("id"));
            subscription.setName(resultSet.getString("name"));
            subscription.setActive(resultSet.getBoolean("isActive"));
            subscription.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
            subscription.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
            subscription.setTariffs(getAllTariffs(subscription.getId()));
        } catch (Exception ex) {
            logger.debug("Problem with searching subscription: " + ex.getMessage());
        }
        logger.debug("subscription searched");
        System.out.println(subscription);
        return subscription;
    }

    @Override
    public Subscription update(Subscription sub) {

        logger.debug("Start  subscription updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY_ID);) {

            pst.setString(1, sub.getName());
            pst.setBoolean(2, sub.isActive());
            pst.setInt(3, sub.getId());


            int status = pst.executeUpdate();
            if (status != 1) throw new SubscriptionException("Updated more than one record!!");

        } catch (Exception ex) {
            logger.error("Problem with updating  subscription: " + ex.getMessage());
        }
        logger.debug(" subscription updated");
        return sub;
    }

    public Subscription updateSub(Subscription sub, String oldName) {
        Subscription subscription = new Subscription();

        logger.debug("Start  subscription updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {

            pst.setString(1, sub.getName());
            pst.setBoolean(2, sub.isActive());
            pst.setString(3, oldName);

            subscription.setName(sub.getName());
            subscription.setActive(sub.isActive());

            int status = pst.executeUpdate();
            if (status != 1) throw new SubscriptionException("Updated more than one record!!");

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
                boolean isActive = resultSet.getBoolean("isActive");
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();

                subscription = new Subscription(id, name, isActive, created, updated);
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

    public List<Tariff> getAllByService(int id) {
        logger.debug("Start  searching all tariffs...");
        List<Tariff> tariffList = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_BY_SERVICE);) {
            SubscriptionDao subscriptionDao = new SubscriptionDao();
            pst.setString(1, findById(id).getName());

            ResultSet result = pst.executeQuery();
            Tariff tariff = null;
            while (result.next()) {
                tariff = new Tariff();
                tariff.setId(result.getInt("id"));
                tariff.setName(result.getString("name"));
                tariff.setPricePerDay(result.getInt("price_per_day"));
                tariff.setType(ServiceType.valueOf(result.getString("service_type")));
                tariff.setActive(result.getBoolean("isActive"));
                tariff.setCreated(result.getTimestamp("created").toLocalDateTime());
                tariff.setUpdated(result.getTimestamp("updated").toLocalDateTime());

                tariffList.add(tariff);
            }
        } catch (Exception ex) {
            logger.debug("Problem with searching all tariffs: " + ex.getMessage());
        }
        logger.debug("All tariffs searched");
        return tariffList;
    }
}

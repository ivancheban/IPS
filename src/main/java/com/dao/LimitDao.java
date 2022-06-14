package com.dao;

import com.exceptions.UserException;
import com.model.Limit;
import com.model.Role;
import com.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class LimitDao implements Dao<Limit>{
    private static final String CREATE_QUERY = "insert into limit(name,amount,isActive,created,updated) values (?,?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from limit where name = ?";
    private static final String UPDATE_QUERY = "UPDATE limit SET item=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE  FROM limit WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from limit";
    private static Logger logger = LogManager.getLogger(LimitDao.class);

    @Override
    public Limit create(Limit limit) {
        logger.debug("Start limit creating");
        if (limit == null) {
            logger.error("limit not found");
            throw new UserException("user is not found");
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY);) {

            pst.setString(1, limit.getName());
            pst.setInt(2,limit.getAmount());
            pst.setBoolean(3, limit.isActive());
            pst.setTimestamp(4, limit.convertToTimestamp(limit.getCreated()));
            pst.setTimestamp(5, limit.convertToTimestamp(limit.getUpdated()));

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Created more than one record!!");

        } catch (Exception ex) {
            logger.debug("Problem with creating limit: " + ex.getMessage());
        }

        logger.debug("Limit created");

        System.out.println(limit.toString());
        return limit;
    }

    @Override
    public Limit findByField(String value) {

       Limit limit = new Limit();

        logger.debug("Start limit searching....");


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {

            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();


            limit.setName(resultSet.getString("name"));
            limit.setAmount(resultSet.getInt("amount"));
            limit.setActive(resultSet.getBoolean("isActive"));
            limit.setCreated(LocalDateTime.parse(resultSet.getString("created")));
            limit.setUpdated(LocalDateTime.parse(resultSet.getString("updated")));


        } catch (Exception ex) {
            logger.debug("Problem with searching limit: " + ex.getMessage());
        }

        logger.debug("Limit searched");

        System.out.println(limit.toString());
        return limit;

    }

    @Override
    public Limit update(Limit item) {
      Limit limit = new Limit();


        logger.debug("Start limit updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {
            pst.setInt(1, limit.getId());

            limit.setName(item.getName());
            limit.setAmount(item.getAmount());
            limit.setActive(item.isActive());
            limit.setCreated(item.getCreated());
            limit.setUpdated(item.getUpdated());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Updated more than one record!!");


        } catch (Exception ex) {

            logger.debug("Problem with updating limit: " + ex.getMessage());
        }

        logger.debug("User updated");
        return limit ;
    }

    @Override
    public boolean delete(int id) {
        boolean status_boolean = false;
        logger.debug("Start limit deleting....");

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
            logger.debug("Problem with deleting limit: " + ex.getMessage());
        }

        logger.debug("Limit deleted");
        return status_boolean;
    }

    @Override
    public List<Limit> findAll() {
        logger.debug("Start  searching all limits....");
        List<Limit> limitList = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
           limitList = new ArrayList<>();
           Limit limit = null;
            while (result.next()) {
                limit = new Limit();
                limit.setId(result.getInt("id"));
                limit.setName(result.getString("name"));
                limit.setAmount(result.getInt("amount"));
                limit.setActive(result.getBoolean("isActive"));
                limit.setCreated(result.getTimestamp("created").toLocalDateTime());
                limit.setUpdated(result.getTimestamp("updated").toLocalDateTime());
                limitList.add(limit);

            }


        } catch (Exception ex) {
            logger.debug("Problem with searching all limits: " + ex.getMessage());
        }

        logger.debug("All Limits searched");
        System.out.println(limitList);

        return limitList;
    }
}

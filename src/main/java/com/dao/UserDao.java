package com.dao;

import com.dto.UserDto;
import com.exceptions.UserException;
import com.model.Role;
import com.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    private static final String CREATE_QUERY = "insert into users(phone,password,isActive,role,created,updated) values (?,?,?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select *  FROM users WHERE phone =? and isActive=true";
    private static final String FIND_BY_ID = "select *  FROM users WHERE id =?";
    private static final String UPDATE_QUERY = "UPDATE users SET phone=?,password=?,isActive=?,role=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from users";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from users limit ?, ?";
    private static Logger logger = LogManager.getLogger(UserDao.class);

    private int noOfRecords;
    Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public UserDao() throws SQLException {
    }

    @Override
    public User create(User user) {
        logger.debug("Start user creating");
        if (user == null) {
            logger.error("user not found");
            throw new UserException("user is not found");
        }
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY);) {
            pst.setString(1, user.getPhone());
            pst.setString(2, user.getPassword());
            pst.setBoolean(3, user.isActive());
            pst.setString(4, String.valueOf(user.getRole()));
            pst.setTimestamp(5, user.convertToTimestamp(user.getCreated()));
            pst.setTimestamp(6, user.convertToTimestamp(user.getUpdated()));

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Created more than one record!!");

        } catch (Exception ex) {
            logger.debug("Problem with creating user: " + ex.getMessage());
        }
        logger.debug("User created");
        return user;
    }

    @Override
    public User findByField(String phone) {
        logger.debug("Start user searching....");
        User user = new User();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {
            pst.setString(1, phone);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setActive(resultSet.getBoolean("isActive"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();
            }
        } catch (SQLException ex) {
            logger.error("Problem with searching user ", ex);
        }
        logger.debug("User searched");
        return user;
    }
    public User findById(int id) {
        logger.debug("Start user searching....");
        User user = new User();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_ID);) {
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setActive(resultSet.getBoolean("isActive"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                Role role = Role.valueOf(String.valueOf(user.getRole()));
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();
            }
        } catch (SQLException ex) {
            logger.error("Problem with searching user ", ex);
        }
        logger.debug("User searched");
        return user;
    }

    @Override
    public User update(User user) {
        logger.debug("Start user updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {

            pst.setString(1, user.getPhone());
            pst.setString(2, user.getPassword());
            pst.setBoolean(3, user.isActive());
            pst.setString(4, String.valueOf(Role.valueOf(String.valueOf(user.getRole()))));
            pst.setInt(5, user.getId());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Updated more than one record!!");

        } catch (Exception ex) {
            logger.debug("Problem with updating user: " + ex.getMessage());
        }

        logger.debug("User updated");
        return user;
    }

    @Override
    public boolean delete(int id) {

        boolean statusBoolean = false;
        logger.debug("Start user deleting....");

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_QUERY);) {
            pst.setInt(1, id);

            int status = pst.executeUpdate();
            if (status == 1) {
                statusBoolean = true;
            }
            if (status != 1) throw new UserException("Deleted more than one record!!");
            con.close();
        } catch (Exception ex) {
            logger.debug("Problem with deleting user: " + ex.getMessage());
        }

        logger.debug("User deleted");
        return statusBoolean;
    }

    @Override
    public List<User> findAll() {
        logger.debug("Start  searching all users....");
        List<User> userList = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
            while (result.next()) {

                int id = result.getInt("id");
                String phone = result.getString("phone");
                String password = result.getString("password");
                boolean isActive = result.getBoolean("isActive");
                Role role = Role.valueOf(result.getString("role"));
                LocalDateTime created = result.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = result.getTimestamp("updated").toLocalDateTime();

                User user = new User(id, phone, password, isActive, role, created, updated);
                userList.add(user);
            }
        } catch (Exception ex) {
            logger.debug("Problem with searching all users: " + ex.getMessage());
        }
        logger.debug("All Users searched");
        System.out.println(userList + "userList in DAO");
        return userList;
    }

    public List<User> getAll(int offset, int noOfRecords) {

        Connection con = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            con = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> usersList = new ArrayList<>();
        User user = null;
        try {
            pstmt = con.prepareStatement(SQL_CALC_FOUND_ROWS);
            statement = con.createStatement();
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);
            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                boolean isActive = resultSet.getBoolean("isActive");
                Role role = Role.valueOf(resultSet.getString("role"));
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();

                user = new User(id, phone, password, isActive, role, created, updated);
                usersList.add(user);
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
        return usersList;

    }
    public int getNoOfRecords() {
        return noOfRecords;
    }
}

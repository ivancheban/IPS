package com.dao;


import com.exceptions.WalletException;
import com.model.Subscription;
import com.model.Wallet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WalletDao implements Dao<Wallet>{

    private static final String CREATE_QUERY = "insert into wallet(number,balance,customers_id) values (?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from wallet where name = ?";
    private static final String UPDATE_QUERY = "UPDATE wallet SET item=? WHERE name=?";
    private static final String DELETE_QUERY = "DELETE  FROM wallet WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from wallet";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from wallet limit ?, ?";

    private static Logger logger = LogManager.getLogger(WalletDao.class);

    private int noOfRecords;


    @Override
    public Wallet create(Wallet item) throws WalletException {
        logger.debug("Start wallet  creating");
        if (item == null) {
            logger.error("wallet  not found");
            throw new WalletException("wallet  is not found");
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);) {

            pst.setString(1, item.getNumber());
            pst.setInt(2, item.getBalance());
            pst.setInt(3, item.getCustomerId());


            int statusUpdate = pst.executeUpdate();

            if (statusUpdate != 1) throw new WalletException("Created more than one record!!");
            ResultSet keys = pst.getGeneratedKeys();
            if(keys.next()){
                int id = keys.getInt(1);
                item.setId(id);
                System.out.println(item.getId());
            }

        } catch (Exception ex) {
            logger.debug("Problem with creating wallet : " + ex.getMessage());
            throw new WalletException(ex.getMessage(), ex);
        }

        logger.debug("wallet created");


        return item;
    }

    @Override
    public Wallet findByField(String value) {
        return null;
    }

    @Override
    public Wallet update(Wallet item) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Wallet> findAll() {
        logger.debug("Start  searching all wallets...");
        List<Wallet> walletsList = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
            walletsList= new ArrayList<>();
           Wallet wallet = null;
            while (result.next()) {
                wallet = new Wallet();

                wallet.setNumber(result.getString("number"));
                wallet.setBalance(result.getInt("balance"));
                wallet.setCustomerId(result.getInt("customers_id"));


                walletsList.add(wallet);

            }


        } catch (Exception ex) {
            logger.debug("Problem with searching all wallets: " + ex.getMessage());
        }

        logger.debug("All wallets searched");
        System.out.println(walletsList);

        return walletsList;
    }


    public List<Wallet> getAll(int offset,int noOfRecords) {
        Connection con = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            con = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Wallet> walletsList = new ArrayList<>();
        Wallet wallet = null;
        try {
            pstmt = con.prepareStatement(SQL_CALC_FOUND_ROWS);
            statement = con.createStatement();
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);

            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {

                String number = resultSet.getString("number");
                int balance = resultSet.getInt("balance");
                int customers_id = resultSet.getInt("customers_id");


               wallet = new Wallet();
                walletsList.add(wallet);
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
        return walletsList;

    }



    public int getNoOfRecords() {
        return noOfRecords;
    }
}

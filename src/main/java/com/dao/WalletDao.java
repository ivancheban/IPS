package com.dao;


import com.exceptions.WalletException;
import com.model.Wallet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDao implements Dao<Wallet>{

    private static final String CREATE_QUERY = "insert into wallets(number,balance) values (?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from wallets where number = ?";
    private static final String FIND_BY_ID_QUERY = "select * from wallets where id = ?";
    private static final String UPDATE_QUERY = "UPDATE wallets SET item=? WHERE number=?";
    private static final String DELETE_QUERY = "DELETE  FROM wallets WHERE id=?";
    private static final String FIND_ALL_QUERY = "select * from wallets";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from wallets limit ?, ?";

    private static Logger logger = LogManager.getLogger(WalletDao.class);

    private int noOfRecords;


    @Override
    public Wallet create(Wallet wallet) throws WalletException {
        logger.debug("Start wallet creating");
        if (wallet == null) {
            System.out.println("dao dont work");
            logger.error("wallet  not found");
            throw new WalletException("wallet  is not found");
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);) {

            pst.setString(1, wallet.getNumber());
            pst.setDouble(2, wallet.getBalance());



            int statusUpdate = pst.executeUpdate();

            if (statusUpdate != 1) throw new WalletException("Created more than one record!!");
            ResultSet keys = pst.getGeneratedKeys();
            if(keys.next()){
                int id = keys.getInt(1);
                wallet.setId(id);
                System.out.println(wallet.getId());
            }

        } catch (Exception ex) {
            logger.debug("Problem with creating wallet : " + ex.getMessage());
            throw new WalletException(ex.getMessage(), ex);
        }

        logger.debug("wallet created");


        return wallet;
    }

    @Override
    public Wallet findByField(String value) {
        Wallet wallet = new Wallet();
        logger.debug("Start wallet searching....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {

            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            wallet.setId(resultSet.getInt("id"));
            wallet.setNumber(resultSet.getString("number"));
            wallet.setBalance(resultSet.getDouble("balance"));

        } catch(Exception ex){
                logger.debug("Problem with searching wallet: " + ex.getMessage());
            }

            logger.debug("wallet searched");


            return wallet;
        }
    public Wallet findById(int id) {
        Wallet wallet = new Wallet();
        logger.debug("Start wallet searching....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_ID_QUERY);) {

            pst.setInt(1, id);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            wallet.setId(resultSet.getInt("id"));
            wallet.setNumber(resultSet.getString("number"));
            wallet.setBalance(resultSet.getDouble("balance"));

        } catch(Exception ex){
            logger.debug("Problem with searching wallet: " + ex.getMessage());
        }

        logger.debug("wallet searched");


        return wallet;
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


           while (result.next()) {
               Wallet wallet = new Wallet();

                wallet.setNumber(result.getString("number"));
                wallet.setBalance(result.getInt("balance"));

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


               Wallet wallet = new Wallet(number,balance);
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

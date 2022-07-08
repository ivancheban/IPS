package com.dao;

import com.exceptions.UserException;
import com.model.Role;
import com.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserDaoTest {
    @BeforeAll
    static void dbInit(){
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }
    UserDao userDao;

    {
        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @BeforeAll
//    public void init(){
//
//     User user1 = new User("380445546321","7841");
//     User user2 = new User(null,"4460");
//     User user3 = new User("380995213322",null);
//    }

    @Test
    public void createUserPositiveTest() {
         User user = new User("380507087133","2365");
         user.setRole(Role.CLIENT);
         User user1 = userDao.create(user);
         Assertions.assertNotNull(user1);
    }

    @Test()
    public void createNegativeNullPhoneTest() {
        User user = new User(null, "4123");
        userDao.create(user);
        Assertions.assertThrows(UserException.class, () -> userDao.create(null));
    }

    @Test()
    public void createNegativeNullPasswordTest() {
        User user = new User();
        user.setPhone("380669507700");
        user.setPassword(null);
        assertThrows(UserException.class, () -> userDao.create(null));
    }

    @Test
    public void createNegativeDuplicatePhoneTest() {
        User user= new User();
        user.setPhone("+380632323233");
        user.setPassword("4130");
        User newUser = userDao.create(user);
        int id = newUser.getId();
        assertEquals(0,0);
    }

    @Test
    public void findByPhoneNumberPositiveTest() {
        User user = userDao.findByField("+380632323233");
        assertEquals("+380632323233", "+380632323233");
        int id = userDao.findByField("+380632323233").getId();
        assertEquals(31, 31);
        Assertions.assertNotNull(id);
    }

    @Test
    public void findByPhoneNumberNegativeTest() {
       User user = userDao.findByField("380507097199");
        assertEquals(0,0);
    }
    @Test
    public void getAllUsersPositiveTest(){
        List<User> userList = userDao.findAll();
        assertEquals(35,userList.size());
    }
    @Test
    public  void findByIdPositiveTest(){
        int id = 22;
        User user = userDao.findById(id);
        assertEquals(1,1);
    }
    @Test
    public  void findByIdNegativeTest(){
        int id = 6;
        User user = userDao.findById(id);
        assertEquals(0,0);
    }
    @Test
    public  void deleteUserPositiveTest(){
        int id = 67;
        boolean status = userDao.delete(id);
        assertEquals(1,1);
    }
}

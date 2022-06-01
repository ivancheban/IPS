//package com.dao;
//
//import com.exceptions.UserException;
//import com.model.Role;
//import com.model.User;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//
//public class UserDaoTest {
//
//    UserDao userDao;
//
//    {
//        try {
//            userDao = new UserDao();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
////    @BeforeAll
////    public void init(){
////
////     User user1 = new User("380445546321","7841");
////     User user2 = new User(null,"4460");
////     User user3 = new User("380995213322",null);
////    }
//
//    @Test
//    public void createUserPositiveTest() {
//         User user = new User("380507087133","2365");
//         User user1 = userDao.create(user);
//         Assertions.assertNotNull(user1);
//    }
//
//    @Test()
//    public void createNegativeNullPhoneTest() {
//
//
//       User user = new User(null,"4123");
//      userDao.create(user);
//      Assertions.assertThrows(UserException.class, () -> userDao.create(null));
//
//    }
//
//    @Test()
//    public void createNegativeNullPasswordTest() {
//        User user = new User();
//        user.setPhone("380669507700");
//        user.setPassword(null);
//        assertThrows(UserException.class, () -> userDao.create(null));
//
//    }
//
//    @Test
//    public void createNegativeDuplicatePhoneTest() {
//        User user = new User("380507087133","4123");
//        assertThrows(UserException.class, () -> userDao.create(user));
//    }
//
//    @Test
//    public void findByPhoneNumberPositiveTest() {
//        User user = userDao.findByField("380507037445");
//        //assertThat()
//        assertEquals("380507037445", "380507037445");
//        assertEquals(1, 1);
//    }
//
//    @Test
//    public void findByPhoneNumberNegativeTest() {
//        User user = userDao.findByField("380501119974");
//        assertThrows(UserException.class, () -> userDao.findByField("380501119974"));
//
//
//    }
//}

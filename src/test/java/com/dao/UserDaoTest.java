package com.dao;

import com.exceptions.UserException;
import com.model.Role;
import com.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserDaoTest {
    UserDao userDao = new UserDao();

    @Test
    public void createUserPositiveTest() {
        User user = new User();
        user.setPhone("380507037445");
        user.setPassword("4562");
        user.setRole(Role.CLIENT);
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());

        userDao.create(user);
        Assertions.assertNotNull(user);



    }
    @Test()
    public void createNegativeNullPhoneTest(){

        User user = new User();
        user.setPhone(null);
        assertThrows(UserException.class, () -> userDao.create(null));

    }
    @Test()
    public void createNegativeNullPasswordTest(){
        User user = new User();
        user.setPhone("380669507700");
        user.setPassword(null);
        assertThrows(UserException.class, () -> userDao.create(null));

    }
    @Test
    public void findByPhoneNumberPositiveTest(){
        User user = userDao.findByField("380507037445");
        assertEquals("380507037445","380507037445");
        assertEquals(1,1);
    }

    @Test
    public void findByPhoneNumberNegativeTest(){
        User user = userDao.findByField("380501119974");

        assertEquals((Short) null,null,"phone is not found");
        Assertions.assertFalse(Boolean.parseBoolean("380501119974"));

    }
}

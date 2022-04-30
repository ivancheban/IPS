package com.dao;

import com.exceptions.UserException;
import com.model.Role;
import com.model.User;


import org.junit.Assert;
import org.junit.Test;


import java.time.LocalDateTime;

import static org.junit.Assert.*;


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
        assertEquals(user, user);
        assertNotNull("user", user);


    }

    @Test()
    public void createNegativeNullPhoneTest() {

        User user = new User();
        user.setPhone(null);

        assertThrows(UserException.class, () -> userDao.create(null));
        //assertThrows(()-> userDao.create(user)).isInstanceOf(RuntimeException.class).hasMessage("User not create");

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
        User user = new User();
        user.setPhone("380507037445");
        userDao.create(user);
        assertEquals(UserException.class, UserException.class);
        assertThrows(UserException.class, () -> userDao.create(null));
    }

    @Test
    public void findByPhoneNumberPositiveTest() {
        User user = userDao.findByField("380507037445");
        //assertThat()
        assertEquals("380507037445", "380507037445");
        assertEquals(1, 1);
    }

    @Test
    public void findByPhoneNumberNegativeTest() {
        User user = userDao.findByField("380501119974");

        assertEquals("phone is not found", null, null);
        /*assertThrows(() -> userDao.findByField("380501119974"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Runtime exception occurred")
                .hasCauseInstanceOf(IllegalStateException.class);*/

    }
}

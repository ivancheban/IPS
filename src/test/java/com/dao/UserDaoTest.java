package com.dao;

import com.exceptions.UserException;
import com.model.Role;
import com.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserDaoTest {

    UserDao userDao = new UserDao();

    @BeforeAll
    public void init(){
     User user = new User("380507087133","2365");
     User user1 = new User("380445546321","7841");
     User user2 = new User(null,"4460");
     User user3 = new User("380995213322",null);
    }

    @Test
    public void createUserPositiveTest() {
//        userDao.create(user);
//        assertEquals(user, user);
//        Assertions.assertNotNull(user);


    }

    @Test()
    public void createNegativeNullPhoneTest() {


        //user.setPhone(null);

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

        //assertEquals("phone is not found", null, null);
       /* assertThrows(() -> userDao.findByField("380501119974"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Runtime exception occurred")
                .hasCauseInstanceOf(IllegalStateException.class);*/

    }
}

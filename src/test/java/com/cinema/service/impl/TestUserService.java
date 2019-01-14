package com.cinema.service.impl;

import com.cinema.model.dao.impl.UserDAO;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import org.jasypt.util.password.PasswordEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class TestUserService {

    private UserService userService;
    private UserDAO userDAO;
    private PasswordEncryptor bpe;
    private User testUser1;
    private User testUser2;
    private List<User> users;

    @BeforeEach
    void init() {
        userService = new UserService();
        userDAO = Mockito.mock(UserDAO.class);
        userService.userDAO = userDAO;
        bpe = Mockito.mock(PasswordEncryptor.class);
        userService.bpe = bpe;
        testUser1 = new User(1, "user1", "user1", new UserRole(2, "USER"));
        testUser2 = new User(2, "user2", "user2", new UserRole(2, "USER"));
        users = Arrays.asList(testUser1, testUser2);
    }


    @Test
    void test_getUser() {
        Mockito.when(userDAO.getAll()).thenReturn(users);

        User testUser = userService.getUser(testUser1.getLogin());

        Mockito.verify(userDAO).getAll();
        Mockito.verifyNoMoreInteractions(userDAO);
        Assertions.assertEquals(testUser1, testUser);
    }

    @Test
    void test_getAll() {
        Mockito.when(userDAO.getAll()).thenReturn(users);

        List<User> all = userService.getAll();

        Mockito.verify(userDAO).getAll();
        Mockito.verifyNoMoreInteractions(userDAO);
        Assertions.assertEquals(users, all);
    }

    @Test
    void test_createUser() {
        Mockito.when(bpe.encryptPassword(testUser1.getPassword())).thenReturn(testUser1.getPassword());
        testUser1.setId(null);

        userService.createUser(testUser1.getLogin(), testUser1.getPassword());

        Mockito.verify(userDAO).create(testUser1);
        Mockito.verifyNoMoreInteractions(userDAO);
    }

    @Test
    void test_isValid() {
        Mockito.when(bpe.checkPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        boolean isValid = userService.isValid(testUser1, testUser1.getPassword());

        Mockito.verify(bpe).checkPassword(testUser1.getPassword(), testUser1.getPassword());
        Mockito.verifyNoMoreInteractions(userDAO);
        Assertions.assertTrue(isValid);
    }

    @Test
    void test_validateLoginInput() {
        boolean isValid = userService.validateLoginInput("pass");
        Assertions.assertFalse(isValid);
    }

    @Test
    void test_validatePasswordInput() {
        boolean isValid = userService.validatePasswordInput("pass", "pass1");
        Assertions.assertFalse(isValid);
    }

}

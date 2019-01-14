package com.cinema.model.dao.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.util.constants.DAOKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestUserDAO {

    private User testUser;
    private UserDAO dao;

    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setLogin("login");
        testUser.setPassword("password");
        testUser.setRole(new UserRole(1, "ADMIN"));
        dao = (UserDAO) DAOFactory.getDAO(DAOKey.USER_DAO);
    }

    @Test()
    void test_create() {
        boolean exists = false;

        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                exists = true;
                testUser = user;
                break;
            }
        }
        Assertions.assertTrue(exists);

        dao.delete(testUser);
    }

    @Test
    void test_read() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                System.out.println(user.getId());
                testUser = user;
                break;
            }
        }
        System.out.println(testUser.getId());
        User user = dao.read(testUser.getId());
        Assertions.assertEquals(testUser, user);

        dao.delete(testUser);
    }

    @Test
    void test_update() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                testUser = user;
                break;
            }
        }
        testUser.setRole(new UserRole(2, "USER"));
        dao.update(testUser);
        Assertions.assertEquals(testUser, dao.read(testUser.getId()));

        dao.delete(testUser);
    }

    @Test
    void test_delete() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                testUser = user;
                break;
            }
        }
        dao.delete(testUser);
        Assertions.assertNull(dao.read(testUser.getId()));
    }

    @Test
    void test_getAll() {
        List<User> users = dao.getAll();
        Assertions.assertFalse(users.size() <= 1);
    }
}

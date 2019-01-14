package com.cinema.model.dao.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.entity.UserRole;
import com.cinema.util.constants.DAOKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestUserRoleDAO {


    private UserRole testRole;
    private UserRoleDAO dao;

    @BeforeEach
    void init() {
        testRole = new UserRole(3, "TEST_ROLE");
        dao = (UserRoleDAO) DAOFactory.getDAO(DAOKey.USER_ROLE_DAO);
    }

    @Test()
    void test_create() {
        dao.create(testRole);
        Assertions.assertNotNull(dao.read(testRole.getId()));
    }

    @Test
    void test_read() {
        dao.create(testRole);
        Assertions.assertEquals(testRole, dao.read(testRole.getId()));
    }

    @Test
    void test_update() {
        dao.create(testRole);

        testRole.setRoleName("NEW_TEST_ROLE");
        dao.update(testRole);
        Assertions.assertEquals(testRole, dao.read(testRole.getId()));
    }

    @Test
    void test_delete() {
        dao.create(testRole);

        dao.delete(testRole);
        Assertions.assertNull(dao.read(testRole.getId()));
    }

    @Test
    void test_getAll() {
        List<UserRole> users = dao.getAll();
        Assertions.assertFalse(users.size() <= 1);
    }

    @AfterEach
    void delete() {
        dao.delete(testRole);
    }
}

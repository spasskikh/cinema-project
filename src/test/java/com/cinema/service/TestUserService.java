package com.cinema.service;

import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

@DirtiesContext
@SpringBootTest
class TestUserService {

    @Autowired
    private UserService userServiceImpl;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Login", "Password", new UserRole(2L, "USER"));
    }

    @Test
    void test_create() {
        User createdUser = userServiceImpl.create(user);

        Assertions.assertEquals(createdUser, user);
    }

    @Test
    void test_read() {
        User createdUser = userServiceImpl.create(user);
        User readUser = userServiceImpl.read(createdUser.getId());

        Assertions.assertEquals(createdUser, readUser);
    }

    @Test
    void test_update() {
        User createdUser = userServiceImpl.create(user);
        createdUser.setRole(new UserRole(1L, "ADMIN"));
        User updatedUser = userServiceImpl.update(createdUser);

        Assertions.assertEquals(createdUser, updatedUser);
    }

    @Test
    void test_delete() {
        User createdUser = userServiceImpl.create(user);
        userServiceImpl.delete(createdUser.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> userServiceImpl.read(createdUser.getId()));
    }

    @Test
    void test_findByLogin() {
        user.setLogin("UNIQUE LOGIN");
        User createdUser = userServiceImpl.create(user);
        User readUser = userServiceImpl.findByLogin(user.getLogin());

        Assertions.assertEquals(readUser, createdUser);
    }

    @Test
    void test_getAll() {
        Assertions.assertEquals(0, userServiceImpl.getAll().size());
    }
}

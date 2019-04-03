package com.cinema.service;

//import com.other.WebApplicationContextConfig;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

@DirtiesContext
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class TestUserService {

    @Resource
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        user = new User(1L, "Login", "Password", new UserRole(2L, "USER"));
    }

    @Test
    void test_create() {
        User createdUser = userService.create(user);

        Assertions.assertEquals(createdUser, user);
    }

    @Test
    void test_read() {
        User createdUser = userService.create(user);
        User readUser = userService.read(createdUser.getId());

        Assertions.assertEquals(createdUser, readUser);
    }

    @Test
    void test_update() {
        User createdUser = userService.create(user);
        createdUser.setRole(new UserRole(3L, "ANONYMOUS"));
        User updatedUser = userService.update(createdUser);

        Assertions.assertEquals(createdUser, updatedUser);
    }

    @Test
    void test_delete() {
        User createdUser = userService.create(user);
        userService.delete(createdUser.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.read(createdUser.getId()));
    }

    @Test
    void test_findByLogin() {
        user.setLogin("UNIQUE LOGIN");
        User createdUser = userService.create(user);
        User readUser = userService.findByLogin(user.getLogin());

        Assertions.assertEquals(readUser, createdUser);
    }

    @Test
    void test_getAll() {
        Assertions.assertEquals(0, userService.getAll().size());
    }
}

package com.cinema.service;

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
class TestUserRoleService {

    @Autowired
    private UserRoleService userRoleService;
    private UserRole userRole;

    @BeforeEach
    void setUp() {
        userRole = new UserRole(3L, "ANONYMOUS");
    }

    @Test
    void test_create() {
        UserRole createdUserRole = userRoleService.create(userRole);

        Assertions.assertEquals(createdUserRole, userRole);
    }

    @Test
    void test_read() {
        UserRole createdUserRole = userRoleService.create(userRole);
        UserRole readUserRole = userRoleService.read(createdUserRole.getId());

        Assertions.assertEquals(createdUserRole, readUserRole);
    }

    @Test
    void test_update() {
        UserRole createdUserRole = userRoleService.create(userRole);
        createdUserRole.setRoleName("NEW ROLE");
        UserRole updatedUserRole = userRoleService.update(createdUserRole);

        Assertions.assertEquals(createdUserRole, updatedUserRole);
    }

    @Test
    void test_delete() {
        UserRole createdUserRole = userRoleService.create(userRole);
        userRoleService.delete(createdUserRole.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> userRoleService.read(createdUserRole.getId()));
    }

    @Test
    void test_getAll() {
        /*2 - initial number of user roles*/
        Assertions.assertEquals(2, userRoleService.getAll().size());
    }
}

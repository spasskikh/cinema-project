package com.cinema.service;

//import com.other.WebApplicationContextConfig;
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
class TestUserRoleService {

    @Resource
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private UserRoleService userRoleService;
    private UserRole userRole;

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
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

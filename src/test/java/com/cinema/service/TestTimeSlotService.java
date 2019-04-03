package com.cinema.service;

//import com.other.WebApplicationContextConfig;
import com.cinema.model.entity.TimeSlot;
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
import java.time.LocalTime;

@DirtiesContext
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class TestTimeSlotService {

    @Resource
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private TimeSlotService timeSlotService;
    private TimeSlot timeSlot;

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        timeSlot = new TimeSlot(1L, LocalTime.MIDNIGHT, LocalTime.NOON);
    }

    @Test
    void test_create() {
        TimeSlot createdTimeSlot = timeSlotService.create(timeSlot);

        Assertions.assertEquals(createdTimeSlot, timeSlot);
    }

    @Test
    void test_read() {
        TimeSlot createdTimeSlot = timeSlotService.create(timeSlot);
        TimeSlot readTimeSlot = timeSlotService.read(createdTimeSlot.getId());

        Assertions.assertEquals(createdTimeSlot, readTimeSlot);
    }

    @Test
    void test_update() {
        TimeSlot createdTimeSlot = timeSlotService.create(timeSlot);
        createdTimeSlot.setEndTime(LocalTime.now());
        TimeSlot updatedTimeSlot = timeSlotService.update(createdTimeSlot);

        Assertions.assertEquals(createdTimeSlot, updatedTimeSlot);
    }

    @Test
    void test_delete() {
        TimeSlot createdTimeSlot = timeSlotService.create(timeSlot);
        timeSlotService.delete(createdTimeSlot.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> timeSlotService.read(createdTimeSlot.getId()));
    }

    @Test
    void test_getAll() {
        /*4 initial number of time slots*/
        Assertions.assertEquals(4, timeSlotService.getAll().size());
    }
}

package com.cinema.service;

import com.cinema.model.entity.TimeSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;

@DirtiesContext
@SpringBootTest
class TestTimeSlotService {

    @Autowired
    private TimeSlotService timeSlotService;
    private TimeSlot timeSlot;

    @BeforeEach
    void setUp() {
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

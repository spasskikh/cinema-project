package com.cinema.service;

import com.cinema.config.DataConfig;
import com.cinema.config.TilesConfig;
import com.cinema.config.WebApplicationContextConfig;
import com.cinema.model.entity.Seat;
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
import java.util.Arrays;
import java.util.List;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataConfig.class, WebApplicationContextConfig.class})
@WebAppConfiguration
class TestSeatService {

    @Resource
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private SeatService seatService;
    private Seat seat;

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        seat = new Seat(21L, 1);
    }

    @Test
    void test_create() {
        Seat createdSeat = seatService.create(seat);

        Assertions.assertEquals(createdSeat, seat);
    }

    @Test
    void test_read() {
        Seat createdSeat = seatService.create(seat);
        Seat readSeat = seatService.read(createdSeat.getId());

        Assertions.assertEquals(createdSeat, readSeat);
    }

    @Test
    void test_update() {
        Seat createdSeat = seatService.create(seat);
        createdSeat.setNumber(11);
        Seat updatedSeat = seatService.update(createdSeat);

        Assertions.assertEquals(createdSeat, updatedSeat);
    }

    @Test
    void test_delete() {
        Seat createdSeat = seatService.create(seat);
        seatService.delete(createdSeat.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> seatService.read(createdSeat.getId()));
    }

    @Test
    void test_getAll() {
        /*20 - initial number of seats*/
        Assertions.assertEquals(20, seatService.getAll().size());
    }

    @Test
    void test_getFreeSeats() {
        List<Long> seatsId = Arrays.asList(1L,2L,3L);

        Assertions.assertNotEquals(20,seatService.getFreeSeats(seatsId).size());
    }
}

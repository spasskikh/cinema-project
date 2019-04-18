package com.cinema.service;

import com.cinema.model.entity.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@DirtiesContext
@SpringBootTest
class TestSeatService {

    @Autowired
    private SeatService seatService;
    private Seat seat;

    @BeforeEach
    void setUp() {
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
        List<Long> seatsId = Arrays.asList(1L, 2L, 3L);

        Assertions.assertNotEquals(20, seatService.getFreeSeats(seatsId).size());
    }
}

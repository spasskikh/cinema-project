package com.cinema.service;

import com.cinema.model.entity.Movie;
import com.cinema.model.entity.Showtime;
import com.cinema.model.entity.TimeSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

@DirtiesContext
@SpringBootTest
class TestShowtimeService {

    @Autowired
    private ShowtimeService showtimeService;
    private Showtime showtime;

    @BeforeEach
    void setUp() {
        showtime = new Showtime(1L,
                LocalDate.now(),
                new Movie(6L, "Name", "Description", 2019, 120),
                new TimeSlot(6L, LocalTime.NOON, LocalTime.MIDNIGHT));
    }

    @Test
    void test_create() {
        Showtime createdShowtime = showtimeService.create(showtime);

        Assertions.assertEquals(createdShowtime, showtime);
    }

    @Test
    void test_read() {
        Showtime createdShowtime = showtimeService.create(showtime);
        Showtime readShowtime = showtimeService.read(createdShowtime.getId());

        Assertions.assertEquals(createdShowtime, readShowtime);
    }

    @Test
    void test_update() {
        Showtime createdShowtime = showtimeService.create(showtime);
        createdShowtime.setMovie(new Movie(7L, "New Name", "New Description", 2019, 120));
        Showtime updatedShowtime = showtimeService.update(createdShowtime);

        Assertions.assertEquals(createdShowtime, updatedShowtime);
    }

    @Test
    void test_delete() {
        Showtime createdShowtime = showtimeService.create(showtime);
        showtimeService.delete(createdShowtime.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> showtimeService.read(createdShowtime.getId()));
    }

    @Test
    void test_getAll() {
        Assertions.assertEquals(0, showtimeService.getAll().size());
    }

    @Test
    void test_getByDateAndTimeSlot() {
        Showtime createdShowtime = showtimeService.create(showtime);
        Showtime foundShowtime = showtimeService.getByDateAndTimeSlot(createdShowtime.getDate(), createdShowtime.getTimeSlot().getId());

        Assertions.assertEquals(createdShowtime, foundShowtime);
    }

    @Test
    void test_getByMovieAndAfterDate() {
        Showtime createdShowtime = showtimeService.create(showtime);
        Showtime foundShowtime = showtimeService.getByMovieAndAfterDate(createdShowtime.getMovie().getId(), createdShowtime.getDate());

        Assertions.assertEquals(createdShowtime, foundShowtime);
    }
}

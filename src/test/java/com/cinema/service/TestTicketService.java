package com.cinema.service;

import com.cinema.model.entity.*;
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
class TestTicketService {

    @Autowired
    private TicketService ticketService;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket(
                1L,
                LocalDate.now(),
                new User(1L, "Login", "Password", new UserRole(2L, "USER")),
                new Showtime(
                        1L,
                        LocalDate.now(),
                        new Movie(1L, "Name", "Description", 2019, 120),
                        new TimeSlot(1L, LocalTime.NOON, LocalTime.MIDNIGHT)
                ),
                new Seat(1L, 1)
        );
    }

    @Test
    void test_create() {
        Ticket createdTicket = ticketService.create(ticket);
        Assertions.assertEquals(createdTicket, ticket);
    }

    @Test
    void test_read() {
        Ticket createdTicket = ticketService.create(ticket);
        Ticket readTicket = ticketService.read(createdTicket.getId());

        Assertions.assertEquals(createdTicket, readTicket);
    }

    @Test
    void test_update() {
        Ticket createdTicket = ticketService.create(ticket);
        createdTicket.setSeat(new Seat(2L, 2));
        Ticket updatedTicket = ticketService.update(createdTicket);

        Assertions.assertEquals(createdTicket, updatedTicket);
    }

    @Test
    void test_delete() {
        Ticket createdTicket = ticketService.create(ticket);
        ticketService.delete(createdTicket.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> ticketService.read(createdTicket.getId()));
    }

    @Test
    void test_getAll() {
        Assertions.assertEquals(0, ticketService.getAll().size());
    }

    @Test
    void test_getByShowtime() {
        Ticket createdTicket = ticketService.create(ticket);
        Ticket foundTicket = ticketService.getByShowtime(createdTicket.getShowtime().getId());

        Assertions.assertEquals(createdTicket, foundTicket);
    }

    @Test
    void test_getByShowtimeAndSeat() {
        Ticket createdTicket = ticketService.create(ticket);
        Ticket foundTicket = ticketService.getByShowtimeAndSeat(createdTicket.getShowtime().getId(), createdTicket.getSeat().getId());

        Assertions.assertEquals(createdTicket, foundTicket);
    }
}

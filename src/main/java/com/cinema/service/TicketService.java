package com.cinema.service;

import com.cinema.model.entity.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket);

    Ticket read(long id);

    Ticket update(Ticket ticket);

    void delete(long id);

    List<Ticket> getAll();

    Ticket getByShowtime(Long showtimeId);

    Ticket getByShowtimeAndSeat(Long showtimeId, Long seatId);
}

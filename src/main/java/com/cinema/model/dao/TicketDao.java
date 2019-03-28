package com.cinema.model.dao;

import com.cinema.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketDao extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t where t.showtime.id = :showtimeId")
    Ticket getByShowtime(@Param("showtimeId") Long showtimeId);

    @Query("select t from Ticket t where t.showtime.id = :showtimeId and t.seat.id = :seatId")
    Ticket getByShowtimeAndSeat(@Param("showtimeId") Long showtimeId, @Param("seatId") Long seatId);
}

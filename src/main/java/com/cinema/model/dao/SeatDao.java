package com.cinema.model.dao;

import com.cinema.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatDao extends JpaRepository<Seat, Long> {

    @Query("select s from Seat s where s.id not in (:seatsId)")
    List<Seat> getFreeSeats(@Param("seatsId") List<Long> seatsId);
}

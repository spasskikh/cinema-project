package com.cinema.model.dao;

import com.cinema.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatDao extends JpaRepository<Seat, Long> {
}

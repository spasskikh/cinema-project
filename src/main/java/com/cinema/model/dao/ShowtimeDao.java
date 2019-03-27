package com.cinema.model.dao;

import com.cinema.model.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeDao extends JpaRepository<Showtime, Long> {
}

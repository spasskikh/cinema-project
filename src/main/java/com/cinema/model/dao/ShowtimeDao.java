package com.cinema.model.dao;

import com.cinema.model.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ShowtimeDao extends JpaRepository<Showtime, Long> {

    @Query("select s from Showtime s where s.date = :date and s.timeSlot.id = :timeSlotId")
    Showtime getByDateAndTimeSlot(@Param("date")LocalDate date, @Param("timeSlotId") Long timeSlotId);

    @Query("select s from Showtime s where s.timeSlot.id = :movieId and s.date >= :date")
    Showtime getByMovieAndAfterDate(@Param("movieId") Long movieId, @Param("date")LocalDate date);
}

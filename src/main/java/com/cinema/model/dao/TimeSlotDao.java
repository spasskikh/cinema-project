package com.cinema.model.dao;

import com.cinema.model.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotDao extends JpaRepository<TimeSlot, Long> {
}

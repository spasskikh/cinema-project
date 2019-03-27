package com.cinema.service;

import com.cinema.model.entity.TimeSlot;

import java.util.List;

public interface TimeSlotService {

    TimeSlot create(TimeSlot timeSlot);

    TimeSlot read(long id);

    TimeSlot update(TimeSlot timeSlot);

    void delete(long id);

    List<TimeSlot> getAll();
}

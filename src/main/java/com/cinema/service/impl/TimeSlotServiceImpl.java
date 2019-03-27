package com.cinema.service.impl;

import com.cinema.model.dao.TimeSlotDao;
import com.cinema.model.entity.TimeSlot;
import com.cinema.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotDao timeSlotDao;

    @Override
    public TimeSlot create(TimeSlot timeSlot) {
        return timeSlotDao.saveAndFlush(timeSlot);
    }

    @Override
    public TimeSlot read(long id) {
        return timeSlotDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TimeSlot update(TimeSlot timeSlot) {
        return timeSlotDao.saveAndFlush(timeSlot);
    }

    @Override
    public void delete(long id) {
        timeSlotDao.deleteById(id);
    }

    @Override
    public List<TimeSlot> getAll() {
        return timeSlotDao.findAll();
    }
}

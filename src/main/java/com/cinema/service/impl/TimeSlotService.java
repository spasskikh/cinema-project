package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.TimeSlotDAO;
import com.cinema.model.entity.TimeSlot;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;

import java.util.List;

/**
 * Class provides methods for working with TimeSlot entity
 *
 * @author Anton Spasskikh
 */
public class TimeSlotService implements Service {

    /**
     * timeSlotDAO field
     */
    private TimeSlotDAO timeSlotDAO;


    /**
     * constructor without parameters, initializes {@link #timeSlotDAO}
     */
    public TimeSlotService() {
        timeSlotDAO = (TimeSlotDAO) DAOFactory.getDAO(DAOKey.TIMESLOT_DAO);
    }

    /**
     * returns time slot by id
     *
     * @param id time slot id
     * @return time slot instance
     */
    public TimeSlot getTimeSlot(int id) {
        return timeSlotDAO.read(id);
    }

    /**
     * returns all existing time slots
     *
     * @return list of time slots
     */
    public List<TimeSlot> getAll() {
        return timeSlotDAO.getAll();
    }
}

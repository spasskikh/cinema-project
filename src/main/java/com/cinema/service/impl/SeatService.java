package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.SeatDAO;
import com.cinema.model.entity.Order;
import com.cinema.model.entity.Seat;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides methods for working with Seat entity
 *
 * @author Anton Spasskikh
 */
public class SeatService implements Service {

    /**
     * seatDAO field
     */
    private SeatDAO seatDAO;

    /**
     * constructor without parameters, initializes {@link #seatDAO}
     */
    public SeatService() {
        this.seatDAO = (SeatDAO) DAOFactory.getDAO(DAOKey.SEAT_DAO);
    }

    /**
     * returns seat by id
     *
     * @param id seat id
     * @return seat instance
     */
    public Seat getSeat(int id) {
        return seatDAO.read(id);
    }

    /**
     * returns all existing seats
     *
     * @return list of seats
     */
    public List<Seat> getAll() {
        return seatDAO.getAll();
    }

    /**
     * returns free seats by showtime
     *
     * @param alreadyBought already occupied seats
     * @return list of seats
     */
    public List<Seat> getFreeSeats(List<Order> alreadyBought) {
        List<Seat> freeSeats = new ArrayList<>(getAll());
        for (Order o : alreadyBought) {
            freeSeats.remove(o.getSeat());
        }
        return freeSeats;
    }
}

package com.cinema.util.builder;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.SeatDAO;
import com.cinema.model.dao.impl.ShowTimeDAO;
import com.cinema.model.dao.impl.UserDAO;
import com.cinema.model.entity.Order;
import com.cinema.util.constants.DAOKey;

import java.time.LocalDate;

/**
 * Order builder class
 *
 * @author Anton Spasskikh
 */
public class OrderBuilder {

    /**
     * order field
     */
    private Order order;

    /**
     * constructor without parameters
     * instantiates {@link #order}
     */
    public OrderBuilder() {
        this.order = new Order();
    }

    /**
     * @return {@link #order}
     */
    public Order build() {
        return order;
    }

    /**
     * sets id
     *
     * @return builder instance
     */
    public OrderBuilder buildId(Integer id) {
        order.setId(id);
        return this;
    }

    /**
     * sets date
     *
     * @return builder instance
     */
    public OrderBuilder buildDate(LocalDate date) {
        order.setDate(date);
        return this;
    }

    /**
     * sets user
     *
     * @return builder instance
     */
    public OrderBuilder buildUser(Integer id) {
        UserDAO dao = (UserDAO) DAOFactory.getDAO(DAOKey.USER_DAO);
        order.setUser(dao.read(id));
        return this;
    }

    /**
     * sets showtime
     *
     * @return builder instance
     */
    public OrderBuilder buildShowTime(Integer id) {
        ShowTimeDAO dao = (ShowTimeDAO) DAOFactory.getDAO(DAOKey.SHOWTIME_DAO);
        order.setShowTime(dao.read(id));
        return this;
    }

    /**
     * sets seat
     *
     * @return builder instance
     */
    public OrderBuilder buildSeat(Integer id) {
        SeatDAO dao = (SeatDAO) DAOFactory.getDAO(DAOKey.SEAT_DAO);
        order.setSeat(dao.read(id));
        return this;
    }
}

package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.OrderDAO;
import com.cinema.model.entity.Order;
import com.cinema.model.entity.Seat;
import com.cinema.model.entity.ShowTime;
import com.cinema.model.entity.User;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class provides methods for working with Order entity
 *
 * @author Anton Spasskikh
 */
public class OrderService implements Service {

    /**
     * orderDAO field
     */
    private OrderDAO orderDAO;

    /**
     * constructor without parameters, initializes {@link #orderDAO}
     */
    public OrderService() {
        this.orderDAO = (OrderDAO) DAOFactory.getDAO(DAOKey.ORDER_DAO);
    }

    /**
     * returns all existing orders
     *
     * @return list of orders
     */
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    /**
     * returns existing orders by showtime
     *
     * @return list of orders
     */
    public List<Order> getAlreadyBought(ShowTime showTime) {
        return getAll().stream()
                .filter(order -> order.getShowTime().equals(showTime))
                .collect(Collectors.toList());
    }

    /**
     * creates order instance
     *
     * @param user     order user
     * @param showTime order showtime
     * @param seat     order seat
     */
    public void create(User user, ShowTime showTime, Seat seat) {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setUser(user);
        order.setShowTime(showTime);
        order.setSeat(seat);

        orderDAO.create(order);
    }

    /**
     * checks if order already exists
     *
     * @param showTime order user
     * @param seat     order seat
     * @return true if exists
     */
    public boolean exists(ShowTime showTime, Seat seat) {
        for (Order order : getAlreadyBought(showTime)) {
            if (order.getSeat().equals(seat)) {
                return true;
            }
        }
        return false;
    }
}

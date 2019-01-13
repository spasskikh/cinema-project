package com.cinema.service.impl;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.impl.OrderDAO;
import com.cinema.model.entity.Order;
import com.cinema.service.Service;
import com.cinema.util.constants.DAOKey;

import java.util.List;

/**
 * Class provides data for attendance report
 *
 * @author Anton Spasskikh
 */
public class AttendanceService implements Service {

    /**
     * orderDAO field
     */
    private OrderDAO orderDAO;

    /**
     * constructor without parameters, initializes {@link #orderDAO}
     */
    public AttendanceService() {
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
}

package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.Order;
import com.cinema.util.builder.OrderBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class provides operations for working with ORDER table in database
 *
 * @author Anton Spasskikh
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Order order) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.ORDER (DATE, USER_ID, SHOWTIME_ID, SEAT_ID) VALUES (?, ?, ?, ?)")) {
            st.setDate(1, Date.valueOf(order.getDate()));
            st.setInt(2, order.getUser().getId());
            st.setInt(3, order.getShowTime().getId());
            st.setInt(4, order.getSeat().getId());

            st.execute();
        } catch (SQLException | NullPointerException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.ORDER WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createOrder(resultSet);
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Order order) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.ORDER" +
                        " SET DATE = ?, USER_ID = ?, SHOWTIME_ID = ?, SEAT_ID = ?" +
                        " WHERE ID = ?")) {
            st.setDate(1, Date.valueOf(order.getDate()));
            st.setInt(2, order.getUser().getId());
            st.setInt(3, order.getShowTime().getId());
            st.setInt(4, order.getSeat().getId());
            st.setInt(5, order.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Order order) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.ORDER WHERE ID = ?")) {
            st.setInt(1, order.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.ORDER");

            while (resultSet.next()) {
                list.add(createOrder(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return list;
    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private Order createOrder(ResultSet resultSet) throws SQLException {
        OrderBuilder builder = new OrderBuilder();
        return builder
                .buildId(resultSet.getInt("ID"))
                .buildDate(resultSet.getDate("DATE").toLocalDate())
                .buildUser(resultSet.getInt("USER_ID"))
                .buildShowTime(resultSet.getInt("SHOWTIME_ID"))
                .buildSeat(resultSet.getInt("SEAT_ID"))
                .build();
    }

}

package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.ShowTime;
import com.cinema.util.builder.ShowTimeBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with SHOWTIME table in database
 *
 * @author Anton Spasskikh
 */
public class ShowTimeDAO extends AbstractDAO<ShowTime> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.SHOWTIME (DATE, MOVIE_ID, TIME_SLOT_ID) VALUES (?, ?, ?)")) {
            st.setDate(1, Date.valueOf(showTime.getDate()));
            st.setInt(2, showTime.getMovie().getId());
            st.setInt(3, showTime.getTimeSlot().getId());

            st.execute();
        } catch (SQLException | NullPointerException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShowTime read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.SHOWTIME WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createShowTime(resultSet);
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
    public void update(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.SHOWTIME" +
                        " SET DATE = ?, MOVIE_ID = ?, TIME_SLOT_ID = ?" +
                        " WHERE ID = ?")) {
            st.setDate(1, Date.valueOf(showTime.getDate()));
            st.setInt(2, showTime.getMovie().getId());
            st.setInt(3, showTime.getTimeSlot().getId());
            st.setInt(4, showTime.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.SHOWTIME WHERE ID = ?")) {
            st.setInt(1, showTime.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShowTime> getAll() {
        List<ShowTime> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.SHOWTIME");

            while (resultSet.next()) {
                list.add(createShowTime(resultSet));
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
    private ShowTime createShowTime(ResultSet resultSet) throws SQLException {
        ShowTimeBuilder builder = new ShowTimeBuilder();
        return builder
                .buildId(resultSet.getInt("ID"))
                .buildDate(resultSet.getDate("DATE").toLocalDate())
                .buildMovie(resultSet.getInt("MOVIE_ID"))
                .buildTimeSlot(resultSet.getInt("TIME_SLOT_ID"))
                .build();
    }
}

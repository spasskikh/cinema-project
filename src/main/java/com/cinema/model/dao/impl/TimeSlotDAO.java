package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.TimeSlot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with TIME_SLOT table in database
 *
 * @author Anton Spasskikh
 */
public class TimeSlotDAO extends AbstractDAO<TimeSlot> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.TIME_SLOT (TIME_FROM, TIME_TILL) VALUES (?, ?)")) {
            st.setTime(1, Time.valueOf(timeSlot.getFrom()));
            st.setTime(2, Time.valueOf(timeSlot.getTill()));

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeSlot read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.TIME_SLOT WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createTimeSlot(resultSet);
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
    public void update(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.TIME_SLOT" +
                        " SET TIME_FROM = ?, TIME_TILL = ?" +
                        " WHERE ID = ?")) {
            st.setTime(1, Time.valueOf(timeSlot.getFrom()));
            st.setTime(2, Time.valueOf(timeSlot.getTill()));
            st.setInt(3, timeSlot.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.TIME_SLOT WHERE ID = ?")) {
            st.setInt(1, timeSlot.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TimeSlot> getAll() {
        List<TimeSlot> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.TIME_SLOT");

            while (resultSet.next()) {
                list.add(createTimeSlot(resultSet));
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
    private TimeSlot createTimeSlot(ResultSet resultSet) throws SQLException {
        return new TimeSlot(
                resultSet.getInt("ID"),
                resultSet.getTime("TIME_FROM").toLocalTime(),
                resultSet.getTime("TIME_TILL").toLocalTime());
    }
}

package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.TimeSlot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with TIME_SLOT table in database
 *
 * @author Anton Spasskikh
 */
public class TimeSlotDAO extends AbstractDAO<TimeSlot> {

    /**
     * date and time formatter field
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.TIME_SLOT (TIME_FROM, TIME_TILL, DURATION) VALUES (?, ?, ?)")) {
            st.setString(1, timeSlot.getFrom().format(formatter));
            st.setString(2, timeSlot.getTill().format(formatter));
            st.setInt(3, timeSlot.getDuration());

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
                        " SET TIME_FROM = ?, TIME_TILL = ?, DURATION = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, timeSlot.getFrom().format(formatter));
            st.setString(2, timeSlot.getTill().format(formatter));
            st.setInt(3, timeSlot.getDuration());
            st.setInt(4, timeSlot.getId());

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
                LocalTime.parse(resultSet.getString("TIME_FROM"), formatter),
                LocalTime.parse(resultSet.getString("TIME_TILL"), formatter),
                resultSet.getInt("DURATION"));
    }
}

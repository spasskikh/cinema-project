package com.cinema.model.dao.impl;

import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.TimeSlot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotsDAO extends AbstractDAO<TimeSlot> {
    @Override
    public void create(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.TIME_SLOTS VALUES (?, ?, ?, ?)")) {
            st.setInt(1, timeSlot.getId());
            st.setString(2, timeSlot.getFrom().toString());
            st.setString(3, timeSlot.getTill().toString());
            st.setInt(4, timeSlot.getDuration());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public TimeSlot read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.TIME_SLOTS WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return createTimeSlot(resultSet);
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
    }

    @Override
    public void update(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.TIME_SLOTS" +
                        " SET" +
                        "  TIME_FROM = ?" +
                        " ,TIME_TILL = ?" +
                        " ,DURATION = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, timeSlot.getFrom().toString());
            st.setString(2, timeSlot.getTill().toString());
            st.setInt(3, timeSlot.getDuration());
            st.setInt(4, timeSlot.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public void delete(TimeSlot timeSlot) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.TIME_SLOTS WHERE ID = ?")) {
            st.setInt(1, timeSlot.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public List<TimeSlot> getAll() {
        List<TimeSlot> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.TIME_SLOTS");

            while (resultSet.next()) {
                list.add(createTimeSlot(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc);
        }
        return list;
    }

    private TimeSlot createTimeSlot(ResultSet resultSet) throws SQLException {
        return new TimeSlot(
                resultSet.getInt(1),
                LocalTime.parse(resultSet.getString(2)),
                LocalTime.parse(resultSet.getString(3)),
                resultSet.getInt(4));
    }
}

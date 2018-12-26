package com.cinema.model.dao.impl;

import com.cinema.exception.NoSuchDAOExc;
import com.cinema.model.dao.AbstractDAO;
import com.cinema.model.entity.ShowTime;
import com.cinema.util.SessionBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeDAO extends AbstractDAO<ShowTime> {

    @Override
    public void create(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cinema.SHOWTIME " +
                        "(ID, DATE, MOVIE_ID, USER_ID, TIME_SLOTS_ID, SEAT_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?)")) {
            st.setInt(1, showTime.getId());
            st.setString(2, showTime.getDate().format(DateTimeFormatter.ISO_DATE));
            st.setInt(3, showTime.getMovie().getId());
            st.setString(4, "NULL");
            st.setInt(4, showTime.getTimeSlot().getId());
            st.setInt(5, showTime.getSeat().getId());

            st.execute();
        } catch (SQLException | NullPointerException exc) {
            logger.error(exc);
        }
    }

    @Override
    public ShowTime read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cinema.SHOWTIME WHERE ID = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return createSession(resultSet);
        } catch (SQLException exc) {
            logger.error(exc);
            return null;
        }
    }

    @Override
    public void update(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE cinema.SHOWTIME" +
                        " SET" +
                        "  DATE = ?" +
                        " ,MOVIE_ID = ?" +
                        " ,USER_ID = ?" +
                        " ,TIME_SLOTS_ID = ?" +
                        " ,SEAT_ID = ?" +
                        "WHERE ID = ?")) {
            st.setString(1, showTime.getDate().format(DateTimeFormatter.ISO_DATE));
            st.setInt(2, showTime.getMovie().getId());
            st.setInt(3, showTime.getUser().getId());
            st.setInt(4, showTime.getTimeSlot().getId());
            st.setInt(5, showTime.getSeat().getId());
            st.setInt(6, showTime.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public void delete(ShowTime showTime) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM cinema.SHOWTIME WHERE ID = ?")) {
            st.setInt(1, showTime.getId());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }
    }

    @Override
    public List<ShowTime> getAll() {
        List<ShowTime> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM cinema.SHOWTIME");

            while (resultSet.next()) {
                list.add(createSession(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc);
        }
        return list;
    }

    private ShowTime createSession(ResultSet resultSet) throws SQLException {
        SessionBuilder builder = new SessionBuilder();

        try {
            builder.buildId(resultSet.getInt("ID"));
            builder.buildDate(resultSet.getString("DATE"));
            builder.buildMovie(resultSet.getInt("MOVIE_ID"));
            builder.buildUser(resultSet.getInt("USER_ID"));
            builder.buildTimeSlot(resultSet.getInt("TIME_SLOTS_ID"));
            builder.buildSeat(resultSet.getInt("SEATS_ID"));
        } catch (NoSuchDAOExc exc) {
            logger.error(exc);
        }
        return builder.getShowTime();
    }

}
